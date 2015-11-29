#!/bin/bash

PREFIX=/data/remote-corpus
NAME=$(basename $1)
LOCATION=$PREFIX/$1/latest
TARGET=$2

if [ $# -ne 2 ]; then
    echo 'Please check argument.'
    exit 1
fi

tar -cf $NAME.tar -T /dev/null
ln -s $LOCATION $NAME
find -L $NAME -name "*.java" -exec tar rf $NAME.tar {} \;
gzip $NAME.tar
unlink $NAME
mv $NAME.tar.gz $TARGET/

exit 0

