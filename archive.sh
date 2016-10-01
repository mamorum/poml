#!/usr/bin/env bash


# 1.Create poml.jar
## - Create poml.jar using existing pom.xml.
mvn package -DskipTests=true

## - Convert pom.poml to pom.xml.
./dist/poml

## - Create poml.jar using generated pom.xml.
mvn package


# 2.Create tar.gz and zip.
## - Get version and define file name.
POML_VER=`./dist/poml -v`
POML_NAME=poml-$POML_VER

## - Create dirs.
SRC_DIR=$POML_NAME
DST_DIR=archive
mkdir $SRC_DIR
mkdir $DST_DIR

## - Copy sh,cmd,jar to src.
cp -p dist/* $SRC_DIR/

echo "[POML:INFO] Create tar.gz."
tar zcvf $DST_DIR/$POML_NAME.tar.gz $SRC_DIR/*

echo "[POML:INFO] Create zip."
zip $DST_DIR/$POML_NAME.zip $SRC_DIR/*

rm -Rf $SRC_DIR


