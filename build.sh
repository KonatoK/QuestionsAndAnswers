#!/bin/sh
rm -rf build/
mkdir build
javac -cp . src/$1.java -d build -deprecation $2
