#!/bin/bash

# environment
TEMPDIR=/tmp/fetcher
JAR=fetcher.jar
LIBS=lib
DATA=data

# compile
ant clean
ant

# delete previous temporary files
rm -rf $TEMPDIR

# copy files
echo 'Distributing ...'
mkdir $TEMPDIR
mkdir $TEMPDIR/lib
mkdir $TEMPDIR/data
cp $JAR $TEMPDIR/
cp $LIBS/*.jar $TEMPDIR/lib
cp $DATA/*.json $TEMPDIR/data
cp maketar.sh $TEMPDIR/
cp run.sh $TEMPDIR/

# zip
dir=$PWD
cd $TEMPDIR/..
tar -zcf $dir/fetcher.tar.gz fetcher
cd $dir

# clean up
rm -rf $TEMPDIR
echo 'Done.'

