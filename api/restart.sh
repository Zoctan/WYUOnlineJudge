#!/bin/bash
ps -ef | grep java | awk '{print $2}' | xargs kill -9
git pull
mvn package -Dmaven.test.skip=true
nohup java -jar target/api-1.0.jar 2>/dev/null 1>/dev/null &
