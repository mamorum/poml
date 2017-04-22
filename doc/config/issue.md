_Since POML v0.2.2_

This converter covers following tags of `pom.xml`.

- /project/issueManagement
- /project/issueManagement/system
- /project/issueManagement/url


## Example
**poml**
```
issue=
  system:GitHub Issues,
  url:https://github.com/mamorum/poml/issues
---
{{issue}}
```

**converted**
```
  <issueManagement>
    <system>GitHub Issues</system>
    <url>https://github.com/mamorum/poml/issues</url>
  </issueManagement>
```


## Config
```
issue=
  system:v,
  url:v
```

- **Val**: Map(`k:v, k:v, ... k:v`)
