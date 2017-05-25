# properties {{properties}}
```
properties=k>v, k>v, ...
```


## Embedded Property
- `&encoding`
- `&compiler`


## Examples
### Config
**poml**
```
properties=gpg.skip>true, &encoding>UTF-8
```

**converted**
```
  <properties>
    <gpg.skip>true</gpg.skip>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
  </properties>
```

### Config + Layout
**poml**
```
properties=&compiler>1.8
---
{{properties}}
```

**converted**
```
  <properties>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
  </properties>
```
