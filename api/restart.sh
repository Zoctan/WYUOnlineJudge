#!/bin/bash
ps aux | grep java | awk '{print }' | xargs kill -9
git pull
mvn package -Dmaven.test.skip=true
nohup java -jar target/api-1.0.jar
