#!/bin/sh

curl -v -X POST \
 -H "Content-Type:application/json" \
 -H "Authorization: Bearer a69f7b19-801a-46a7-8695-b3a9ea09c245" \
 --data '{"name":"USA"}' \
 http://localhost:8080/countries
