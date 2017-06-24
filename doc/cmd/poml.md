# poml
## Usage
```
poml
```

This command converts `./pom.poml` to `./pom.xml`.


## Option Usage
```
poml [option]
```

### `-h`, `help`
Print usage and options.

### `-v`, `version`
Print poml version.

### `mkdirs`
Create following src directories for maven project

- src/main/java
- src/main/resources
- src/test/java
- src/test/resources

### `init`
Create following resources to init maven project.

- `pom.poml`
- `pom.xml` (converted from  `pom.poml`)
- src directories (created by `mkdirs`)

This option asks some questions to create `pom.poml`.


## Note: With Maven Command
```
poml && mvn <goal>
```

Above executes `mvn` with successfully converted `pom.xml`.  
Operater `&&` seems available also in the Windows. (see [Command shell overview - MS TechNet](https://technet.microsoft.com/en-us/library/bb490954.aspx).)
