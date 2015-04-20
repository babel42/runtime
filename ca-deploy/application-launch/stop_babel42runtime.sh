#!/bin/bash

docker-compose stop
docker ps -aq | cut -f 1 -d ' ' >tmp
docker stop `cat tmp`
docker rm `cat tmp`
rm tmp
docker rmi `docker images | grep babel | awk '{print $3}'`
