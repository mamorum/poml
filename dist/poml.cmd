@echo off
setlocal

set POML_ARG=%*
if "%POML_ARG%"=="" (
  java.exe -jar %~dp0\poml.jar pom.poml pom.xml
) else (
  java.exe -jar %~dp0\poml.jar %POML_ARG%
)

@REM # Not using exit (exit /b) to set %errorlevel%.
@REM # Because it is set by java.exe.
@REM # If java.exe is not found, Win set error code to it.
