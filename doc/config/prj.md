This converter covers following tags of `pom.xml`.

- /project
- /project/modelVersion


## Example
**poml**
```
---
{{#model4}}
  ...
{{/model4}}
```

**converted**
```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  ...
</project>
```


## Config
Nothing to configure. Please use in layout section. 
