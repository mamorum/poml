
Convert `pom.poml` to `pom.xml`.


### Usage
```
poml [option]
```


### Options
##### `-h`, `help`
Print command line usage and options.

##### `-v`, `version`
Print poml version.

##### `mkdirs`
Create following directories for maven project

- src/main/java
- src/main/resources
- src/test/java
- src/test/resources


### Note: With Maven Command
Combined command like `poml && mvn test` is convenient to execute Maven with successfully generated `pom.xml`. 

```
demo$ poml && mvn <goal>
```

Operater `&&` seems available also in the Windows (see [Command shell overview - MS TechNet](https://technet.microsoft.com/en-us/library/bb490954.aspx)).
