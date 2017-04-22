_Since POML v0.2.2_

This converter covers following tags of `pom.xml`.

- /project/scm
- /project/scm/url
- /project/scm/connection
- /project/scm/developerConnection
- /project/scm/tag


## Example
**poml**
```
scm=
  url:https://github.com/mamorum/poml,
  connect:scm:git:https://github.com/mamorum/poml.git,
  devConnect:scm:git:git@github.com:mamorum/poml.git,
  tag:HEAD
---
{{scm}}
```

**converted**
```
  <scm>
    <url>https://github.com/mamorum/poml</url>
    <connection>scm:git:https://github.com/mamorum/poml.git</connection>
    <developerConnection>scm:git:git@github.com:mamorum/poml.git</developerConnection>
    <tag>HEAD</tag>
  </scm>
```


## Config
```
scm=
  url:v,
  connect:v,
  devConnect:v,
  tag:v
```

- **Val**: Map(`k:v, k:v, ... k:v`)
