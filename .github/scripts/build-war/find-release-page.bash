#!/usr/bin/env bash

set -eu

RELEASE_PAGE_URL="https://api.github.com/repos/footaku/scratches/releases"

function find-page {
    echo "$(curl -fsL --request GET \
      -H "Accept: application/vnd.github.v3+json" \
      -H "Authorization: Bearer ${1}" \
      -H "Content-Type: application/json" \
      "${RELEASE_PAGE_URL}" | jq -r ".[] | select(.name == \"${2}\") | .id")"
}

function page-name {
    echo $(printf "${1}" | jq -s -R -r @uri)
}

## tagを受け取ってリリースページを探す
find-page ${1} "$(page-name ${2})"
