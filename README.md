# Java Convert Tool

## Introduction

This project is a Java Swing application, using Spring Boot, to process basic converting operations.

## How to

### Launch the tool

Launch it directly with Maven : ```mvn spring-boot:run```. 

### Set it up for regular usage

- build with Maven : ```mvn clean package```
- go to ```target``` folder
- take a copy of the jar, ```application.properties```, ```convert-tool.sh```, the icon and store them somewhere, like in your home folder
- open ```application.properties``` and check that log path is ok for you (default is ```/tmp```) 
- launch the tool with a terminal : ```$HOME/convert-tool.sh``` 
- alternate way : with a terminal, go to jar folder and launch ```java -jar convert-tool-1.0.0.jar```

On Linux, you can create a launcher, calling the sh script the same way. <br/><br/>
icon source : https://commons.wikimedia.org/wiki/File:Tools_blue.svg

### Know errors

1) the tool doesn't start 
- make sure java is installed
- set full jdk path in the sh script which is launching the project
```bash
/usr/lib/jvm/jdk11/bin/java -jar ${folder}/convert-tool-1.0.7.jar --spring.config.location=file:${folder}/application.properties
```

## Application features

The tool will apply a selected action, from a combobox, to some content, entered in the left panel of the application.  
After clicking on "convert", the result will be displayed in the right panel of the application.  
Instead of clicking on the "convert" button, user can press CTRL + ENTER.

### Trim a file

This feature with clear all line breaks from a file content.

### Trim a json file

This feature will parse a json content, and remove all the line breaks.  
An error will be displayed if json content is not valid.

### Trim an xml file

This feature will parse an xml content, and remove all the line breaks.  
An error will be displayed if xml content is not valid.

### Display a json file

This feature will parse a json content, and display it with indentation.  
The indentation factor used by this process is specified in ```application.properties```.  
An error will be displayed if json content is not valid.

### Display a xml file

This feature will parse a xml content, and display it with indentation.  
The indentation factor used by this process is specified in ```application.properties```.  
An error will be displayed if xml content is not valid.

### Convert a json file to xml

This feature will parse a json content, and convert it to xml.  
Default xml header and root tag values will be taken from ```application.properties```.  
After that, the xml content will be displayed with indentation.  
The indentation factor used by this process is specified in ```application.properties```.  
An error will be displayed if json content is not valid.

### Convert a xml file to json

This feature will parse a xml content, and convert it to json.  
After that, the json content will be displayed with indentation.  
The indentation factor used by this process is specified in ```application.properties```.  
An error will be displayed if xml content is not valid.

### Convert a csv file to json

This feature will read a csv file, using separator character specified in ```application.properties```.  
After that, the file content will be used to create a json content, displayed with indentation.  
The indentation factor, used to display the json content, is specified in ```application.properties```.

### Lower

This feature will convert a text to lower cases.

### Upper

This feature will convert a text to upper cases.