This converter covers following tags of `pom.xml`.

- /project/groupId
- /project/artifactId
- /project/version
- /project/packaging


## Example
**poml**
```
pkg=com.example:demo:0.0.1:jar
---
{{pkg}}
```

**converted**
```
  <groupId>com.example</groupId>
  <artifactId>demo</artifactId>
  <version>0.0.1</version>
  <packaging>jar</packaging>
```


## Config
```
pkg=groupId:artifactId:version:packaging
```

- **Val**: String(`v`)
- **Required**: groupId, artifactId, version

```
# Without packaging
pkg=groupId:artifactId:version
```

