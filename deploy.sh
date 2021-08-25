#!/usr/bin/env bash

echo "Building app ..."
mvn clean install

cd target/

java -jar ms-atos-user.jar
echo "App started ."

$SHELL
