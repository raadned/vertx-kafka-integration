#!/bin/bash

if [ $# -eq 0 ]; then
    echo "Payload file needs to be provided!"
    exit 1
fi

payload=$1

POST_EVENT="http://localhost:8080/events"

curl -X POST -d @$payload $POST_EVENT --header "Content-Type:application/json"

