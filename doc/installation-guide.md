# POML -  Installation Guide
### 1. Requirement
Poml requires a JDK8 or higher.

Poml uses `java` command in the `PATH` (`Path`) evnvironment variable. Please add `$JAVA_HOME/bin` (`%JAVA_HOME%\bin`) to the `PATH`.


### 2. Recommendation
It is recommended to have the [Maven](https://maven.apache.org/index.html) installed.


### 3. Download
Distribution archive is available on the [Release Page](https://github.com/mamorum/poml/releases).


### 4. Extract
Extract the archive in any directory, like following.

```
unzip poml-0.2.1.zip
```

```
tar zxvf poml-0.2.1.tar.gz
```


### 5. Update PATH
Add the extracted directory `poml-0.2.1` to the `PATH`, like following.

```
export PATH=$PATH:/opt/poml-0.2.1
```


### 6 Check
Check `poml` command with option `-v`.

```
poml -v
```

If version number appears, installation has succeeded.
