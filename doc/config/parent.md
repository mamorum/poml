_Since POML v0.3.0_

This converter covers following tags of `pom.xml`.

- /project/parent/groupId
- /project/parent/artifactId
- /project/parent/version


## Example
**poml**
```
parent=org.springframework.boot:spring-boot-starter-parent:1.5.1.RELEASE
---
{{parent}}
```

**converted**
```
  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>1.5.1.RELEASE</version>
  </parent>
```


## Config
```
parent=groupId:artifactId:version
```

- **Val**: String(`v`)
- **Required**: groupId, artifactId, version


## Note: XML element "parent/relativePath" 
Now, this converter does not support "parent/relativePath" of `poml.xml`.
