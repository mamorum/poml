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
properties=&encoding>UTF-8
```

**converted**
```
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
  </properties>
```

### Config + Layout
**poml**
```
properties=&compiler>1.8, gpg.skip>true
---
{{properties}}
```

**converted**
```
  <properties>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
    <gpg.skip>true</gpg.skip>
  </properties>
```
