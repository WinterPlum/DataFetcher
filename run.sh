#!/bin/bash

RESULT=result
CHUNK=$1

if [ ! -d data/$CHUNK.json ]; then
    echo 'Json file does not exist'
    exit 1
fi

if [ -d $RESULT/$CHUNK ]; then
    echo 'Result already exists'
    exit 2
fi

mkdir -p $RESULT
mkdir -p $RESULT/$CHUNK

java -cp fetcher.jar:lib/gson-2.3.1.jar:. edu.utexas.datafetcher.DataFetcher $CHUNK

exit 0

