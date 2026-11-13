@echo off

set folder=C:\Users\someone\apps\java-convert-tool


rem this version will not show the console
start javaw -jar %folder%\convert-tool-1.0.8.jar --spring.config.location=file:%folder%\application.properties


rem -------- variant mentioning full jdk path --------------
rem jdk 11
rem set jdk="%ProgramFiles%\Java\jdk-11\bin"

rem start "" %jdk%\javaw -jar %folder%\convert-tool-1.0.8.jar --spring.config.location=file:%folder%\application.properties