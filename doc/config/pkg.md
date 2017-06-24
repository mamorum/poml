# pkg {{pkg}}
```
pkg=groupId:artifactId:version:packaging
```


## Examples
### Config
**poml**
```
pkg=com.example:demo:0.0.1:jar
```

**converted**
```
  <groupId>com.example</groupId>
  <artifactId>demo</artifactId>
  <version>0.0.1</version>
  <packaging>jar</packaging>
```

### Config + Layout
**poml**
```
pkg=com.example:demo:0.0.1
---
{{pkg}}
```

**converted**
```
  <groupId>com.example</groupId>
  <artifactId>demo</artifactId>
  <version>0.0.1</version>
```