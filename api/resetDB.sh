#!/bin/bash
for i in $(find src/test/resources/dev/sql/*.sql) ; do
  echo ${i};
  mysql -uroot -proot api_dev < ${i};
done
