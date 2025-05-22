#!/bin/bash

javac -d build/ src/*.java
java -cp build/ Interpretador $1