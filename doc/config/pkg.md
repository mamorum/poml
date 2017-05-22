# pkg
```
pkg=groupId:artifactId:version:packaging
---
{{pkg}}
```

- **Required**: groupId, artifactId, version
- **Optional**: packaging


## Example 1
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


## Example 2
**poml**
```
pkg=com.example:demo:0.0.1
```

**converted**
```
  <groupId>com.example</groupId>
  <artifactId>demo</artifactId>
  <version>0.0.1</version>
```