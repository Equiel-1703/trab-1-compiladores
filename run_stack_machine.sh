#!/bin/bash

javac -d build/ src/*.java
java -cp build/ MaquinaPilha $1