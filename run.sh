#!/bin/bash

# $1 = path to Java file
FILE="$1"

# check if file exists
if [ ! -f "$FILE" ]; then
  echo "File not found: $FILE"
  exit 1
fi

# clear old compiled classes
rm -rf .bin/*
mkdir -p .bin

# compile the Java file with all jars in lib/
javac -cp "lib/*" -d .bin "$FILE"

# figure out fully qualified class name from package
PACKAGE=$(grep -E '^package ' "$FILE" | sed 's/package //;s/;//')
FILENAME=$(basename "$FILE")
CLASSNAME="${FILENAME%.*}"

if [ -n "$PACKAGE" ]; then
  FULLCLASS="$PACKAGE.$CLASSNAME"
else
  FULLCLASS="$CLASSNAME"
fi

# run the class
java -cp ".bin:lib/*" "$FULLCLASS"
