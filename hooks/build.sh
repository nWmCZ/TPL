#!/bin/sh

SHORTHASH=`git rev-parse --short HEAD`

gradle clean build

docker build --build-arg BUILD_DATE=`date -u +"%Y-%m-%dT%H:%M:%SZ"` --build-arg VCS_REF=$SHORTHASH -t novst/tpl .
