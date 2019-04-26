#!/usr/bin/env bash

docker-compose rm -f -s
docker-compose up --build
docker rmi $(docker images -f "dangling=true" -q) -f
