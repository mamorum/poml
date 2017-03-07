# POML -  Installation Guide
## Requirements
- **JDK**: version 8 is installed.
- **PATH (or Path)**: `$JAVA_HOME/bin` (or `%JAVA_HOME%\bin`) is added.

```
$ java -version
java version "1.8.0_...
```

## Install
### 1. Download
Get a file from [Release Page](https://github.com/mamorum/poml/releases). (not "Source code" file.)

```
# wget https://github.com/mamorum/poml/releases/download/v0.3.0/poml-0.3.0.tar.gz
```


### 2. Extract
Extract it in any directory.

```
# tar zxvf poml-0.3.0.tar.gz
```


### 3. Update PATH
Add the extracted directory to the `PATH` (or `Path`).

```
$ export PATH=$PATH:/opt/poml-0.3.0
```


## Check
Execute `poml -v` command.

```
$ poml -v
0.3.0
```


