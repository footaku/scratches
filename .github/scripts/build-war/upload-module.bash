#!/usr/bin/env bash

set -eu

RELEASE_PAGE_URL_PREFIX="https://uploads.github.com/repos/footaku/scratches/build-war/releases"

## アップロード対象のJARとWARを列挙する
function modules {
    find . -type f -regex "\./\(.*\)/build/.*\.[j|w]ar" | \
        grep -v -E "common|buildSrc|local-env" | \
        sort
}

## GitHubのReleaseページのAssetsとしてアップロードする
function upload {
    local -r assets_url="${RELEASE_PAGE_URL_PREFIX}/${2}/assets"
    echo "$(modules)" | while read line; do
        curl -vv --request POST \
            -H "Accept: application/vnd.github.v3+json" \
            -H "Authorization: Bearer ${1}" \
            -H "Content-Type: application/octet-stream" \
            --data-binary @"${line}" \
            "${assets_url}?name=$(basename ${line})"
    done
}

## ReleaseページのIDを受け取ってアップロード
upload ${1} ${2}
