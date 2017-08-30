# parent {{parent}}
```
parent=groupId:artifactId:version:relativePath
```


## Examples
### Config
**poml**
```
parent=com.example:demo-parent:0.0.1
```

**converted**
```
  <parent>
    <groupId>com.example</groupId>
    <artifactId>demo-parent</artifactId>
    <version>0.0.1</version>
  </parent>
```

### Config + Layout
**poml**
```
parent=com.example:demo-parent:0.0.1:../pom.xml
---
{{parent}}
```

**converted**
```
  <parent>
    <groupId>com.example</groupId>
    <artifactId>demo-parent</artifactId>
    <version>0.0.1</version>
    <relativePath>../pom.xml</relativePath>
  </parent>
```
