#!/bin/bash

EVENTS_ENDPOINT="http://localhost:8080/events"
if [ $# -eq 0 ]; then
    echo "Payload file needs to be provided!"
    exit 1
fi

EVENT_PAYLOAD=$1

curl -X POST -d @$EVENT_PAYLOAD $EVENTS_ENDPOINT --header "Content-Type:application/json"

