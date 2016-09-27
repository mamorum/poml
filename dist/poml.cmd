@echo off

setlocal

@REM # Find java.exe
for %%I in (java.exe) do set POML_JAVA=%%~$PATH:I
if "%POML_JAVA%"=="" set POML_JAVA=java.exe

@REM # Execute jar
echo [POML:INFO] Execute "poml.jar" using "%POML_JAVA%"
set POML_ARG=%*
if "%POML_ARG%"=="" (
  %POML_JAVA% -jar %~dp0\poml.jar pom.poml pom.xml
) else (
  %POML_JAVA% -jar %~dp0\poml.jar %POML_ARG%
)

@REM # Not using exit (exit /b) command.
@REM # Because ERRORLEVEL is set by java.exe.
@REM # If java.exe is not found, Win(7) set 9009 to ERRORLEVEL.
