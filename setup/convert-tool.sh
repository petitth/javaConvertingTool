#!/bin/bash
folder=$HOME
java -jar ${folder}/convert-tool-1.0.8.jar --spring.config.location=file:${folder}/application.properties
exit 0