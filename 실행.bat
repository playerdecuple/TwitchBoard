@echo off
cls
color fd rem zz
title TwitchBoard
java -jar -Dfile.encoding=UTF-8 %cd%\out\artifacts\TwitchBoard_jar\TwitchBoard.jar
pause