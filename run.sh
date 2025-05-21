#!/bin/bash

javac -d build/ src/*.java
java -cp build/ Compilador $1