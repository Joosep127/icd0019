#!/bin/bash

# -----------------------------
# Universal Java Runner Script
# -----------------------------

SRC_DIR="src"
BIN_DIR=".bin"
LIB_DIR="lib"
FILE_DIR=$1

rm -rf "$BIN_DIR"/*
mkdir -p "$BIN_DIR"

javac -cp "lib/junit-platform-console-standalone-1.11.4.jar" -d ".bin" $FILE_DIR

java -jar lib/junit-platform-console-standalone-1.11.4.jar --class-path=.bin:src --include-classname=.* --scan-classpath --details=verbose
