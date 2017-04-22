This converter covers following tag of `pom.xml`.

- /project/properties


## Example
**poml**
```
property=
  $encoding:UTF-8,
  $compiler:1.8,
  gpg.skip:true
---
{{property}}
```

**converted**
```
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
    <gpg.skip>true</gpg.skip>
  </properties>
```


## Config
```
property=k1:v1, k2:v2
```

- **Val**: Map(`k:v, k:v, ... k:v`)
- Optional:
    - $encoding: converted to tags `project.build.sourceEncoding` and `project.reporting.outputEncoding`
    - $compiler: converted to tags `maven.compiler.source` and `maven.compiler.target`
