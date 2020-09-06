#!/usr/bin/env bash

set -eu

RELEASE_PAGE_URL_PREFIX="https://api.github.com/repos/footaku/scratches/releases/tags"

function tag {
    echo "$(curl -fsL --request GET \
      -H "Accept: application/vnd.github.v3+json" \
      -H "Authorization: Bearer ${1}" \
      -H "Content-Type: application/json" \
      "${RELEASE_PAGE_URL_PREFIX}/$(printf "${2}" | jq -s -R -r @uri)" |
      jq -r .id)"
}

## tagを受け取ってリリースページを探す
tag ${1} ${2}
