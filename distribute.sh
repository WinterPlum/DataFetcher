#!/bin/bash

# environment
TEMPDIR=/tmp/fetcher
JAR=fetcher.jar
LIBS=lib
DATA=data

# compile
ant clean
ant

# copy files
echo 'Distributing ...'
mkdir $TEMPDIR
mkdir $TEMPDIR/lib
mkdir $TEMPDIR/data
cp $JAR $TEMPDIR/
cp $LIBS/*.jar $TEMPDIR/lib
cp $DATA/muse20151125.json $TEMPDIR/data

# zip
dir=$PWD
cd $TEMPDIR/..
tar -zcf $dir/fetcher.tar.gz fetcher
cd $dir

# clean up
rm -rf $TEMPDIR
echo 'Done.'

