@echo off

if "%*"=="" (
  java.exe -jar %~dp0\poml.jar pom.poml pom.xml
) else (
  java.exe -jar %~dp0\poml.jar %*
)
