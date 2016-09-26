@echo off
setlocal

@rem -> find mvn and java
for %%I in (mvn.cmd) do set CMD_MVN=%%~$PATH:I
for %%I in (java.exe) do set CMD_JAVA=%%~$PATH:I

@rem -> check mvn and java
if not exist "%CMD_MVN%" (
  echo [POML:ERROR] "mvn.cmd" is not found
  echo [POML:ERROR] Please install maven and set %%MAVEN_HOME%%\bin to the PATH
  exit /b 1
)
if not exist "%CMD_JAVA%" (
  echo [POML:ERROR] "java.exe" is not found
  echo [POML:ERROR] Please install jdk and set %%JAVA_HOME%%\bin to the PATH
  exit /b 1
)

@rem -> check argument
set ARG_POML=%*
set OPTIONAL=false
if "%ARG_POML%"=="-v" set OPTIONAL=true
if "%ARG_POML%"=="-h" set OPTIONAL=true

@rem -> execute jar
echo [POML:INFO] Using "%CMD_JAVA%"
if %OPTIONAL%==true (
  %CMD_JAVA% -jar %~dp0\poml.jar %ARG_POML%
  exit /b %ERRORLEVEL%
) else (
  %CMD_JAVA% -jar %~dp0\poml.jar pom.poml pom.xml
  if errorlevel 1 (exit /b %ERRORLEVEL%)
)

@rem -> execute mvn
set ARG_MVN=%ARG_POML%
if "%ARG_MVN%"=="" (exit /b %ERRORLEVEL%)
echo [POML:INFO] Execute maven using "%CMD_MVN%"
%CMD_MVN% %ARG_MVN%

exit /b %ERRORLEVEL%
