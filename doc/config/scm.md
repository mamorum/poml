# scm {{scm}}
```
scm=connection>v, developerConnection>v, tag>v, url>v
```


## Examples
### Config
**poml**
```
scm=
  connection>scm:git:https://github.com/mamorum/poml.git,
  developerConnection>scm:git:git@github.com:mamorum/poml.git,
  tag>HEAD, url>https://github.com/mamorum/poml
```

**converted**
```
  <scm>
    <connection>scm:git:https://github.com/mamorum/poml.git</connection>
    <developerConnection>scm:git:git@github.com:mamorum/poml.git</developerConnection>
    <tag>HEAD</tag>
    <url>https://github.com/mamorum/poml</url>
  </scm>
```

### Config + Layout
**poml**
```
scm=url>https://github.com/mamorum/poml
---
{{scm}}
```

**converted**
```
  <scm>
    <url>https://github.com/mamorum/poml</url>
  </scm>
```