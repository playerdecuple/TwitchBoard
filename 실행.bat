@echo off
cls
color fd
title TwitchBoard
java -jar -Dfile.encoding=UTF-8 %cd%\out\artifacts\TwitchBoard_jar\TwitchBoard.jar
pause
