# POML - Installation Guide
## Requirements
- **JDK**: version 8 is installed.
- **PATH (or Path)**: `$JAVA_HOME/bin` (or `%JAVA_HOME%\bin`) is added.

```
$ java -version
java version "1.8.0_...
```

## Install
Following commands is for version `1.0.0`. Please replace `1.0.0` with the version which you will install.

### 1. Download
Get a file from [Release Page](https://github.com/mamorum/poml/releases). (not "Source code" file.)

```
# wget https://github.com/mamorum/poml/releases/download/v1.0.0/poml-1.0.0.tar.gz
```


### 2. Extract
Extract it in any directory.

```
# tar zxvf poml-1.0.0.tar.gz
```


### 3. Update PATH
Add the extracted directory to the `PATH` (or `Path`).

```
$ export PATH=$PATH:/opt/poml-1.0.0
```


### 4. Check
Execute `poml -v` command.

```
$ poml -v
1.0.0
```


