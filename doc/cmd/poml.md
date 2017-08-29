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

### `jardir`, `mkdirs`
Create following src directories for maven jar project.

- src/main/java
- src/main/resources
- src/test/java
- src/test/resources

### `wardir`
Create following src directories for maven war project.

- src/main/webapp/WEB-INF
- dirs created by `jardir`

### `init`
Create following resources to init maven project.

- `pom.poml`
- `pom.xml` (converted from  `pom.poml`)
- src directories (created by `jardir` or `wardir`, depending on project's packaging)

This option asks some questions to create `pom.poml`.


## Note: With Maven Command
```
poml && mvn <goal>
```

Above executes `mvn` with successfully converted `pom.xml`.  
Operater `&&` seems available also in the Windows. (see [Command shell overview - MS TechNet](https://technet.microsoft.com/en-us/library/bb490954.aspx).)
