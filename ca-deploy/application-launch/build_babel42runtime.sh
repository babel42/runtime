#!/bin/bash

rm -rf ~/git/runtime/ca-ingest/target/*
rm -rf ~/git/runtime/ca-framework/target/*
rm -rf ~/git/runtime/ca-outgoing/target/*
rm -rf ~/.m2/repository/com/chirpanywhere/*

cd ~/git/runtime/ca-outgoing
mvn install
cd ~/git/runtime/ca-framework
mvn install
cd ~/git/runtime/ca-ingest
mvn install

cp ~/git/runtime/ca-ingest/target/ca-ingest-0.1.0.jar ~/dev/docker/docker-java
cd ~/dev/docker/docker-java
docker build -t babel42/runtime:v2 .

