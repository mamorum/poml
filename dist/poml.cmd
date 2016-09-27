@echo off

setlocal

@rem -> find java
for %%I in (java.exe) do set POML_JAVA=%%~$PATH:I
if "%POML_JAVA%"=="" set POML_JAVA=java.exe

@rem -> execute jar
echo [POML:INFO] Execute "poml.jar" using "%POML_JAVA%"
set POML_ARG=%*
if "%POML_ARG%"=="" (
  %POML_JAVA% -jar %~dp0\poml.jar pom.poml pom.xml
) else (
  %POML_JAVA% -jar %~dp0\poml.jar %POML_ARG%
)
