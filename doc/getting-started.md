# Poml Getting Started

<!--
## Table of Contents
1. Install
2. Usage
-->

## 1. Install
### 1.1. Requirement
Poml requires a JDK8 or higher. Poml uses `java` command in the `PATH` evnvironment variable. Please add `$JAVA\_HOME/bin` (or `%JAVA_HOME%\bin`) to the `PATH`.

And, it is recommended to have the [Maven](https://maven.apache.org/index.html) installed.


### 1.2. Download
You can download the distribution archive from [Release Page](https://github.com/mamorum/poml/releases).


### 1.3. Extract
Extract downloaded archive in any directory, like following.

```
unzip poml-0.1.0.zip
```

```
tar zxvf poml-0.1.0.tar.gz
```


### 1.4. Update PATH
Add the extracted directory `poml-0.1.0` to the `PATH`, like following.

```
export PATH=$PATH:/opt/poml-0.1.0
```

### 1.5 Check
Check `poml` command with option `-v`.

```
poml -v
```

Output version shows that installing is completed.
