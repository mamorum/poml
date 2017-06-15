# issue {{issue}}
```
issue=system>v, url>v
```


## Examples
## Config
**poml**
```
issue=
  system>GitHub Issues,
  url>https://github.com/mamorum/poml/issues
```

**converted**
```
  <issueManagement>
    <system>GitHub Issues</system>
    <url>https://github.com/mamorum/poml/issues</url>
  </issueManagement>
```

## Config + Layout
**poml**
```
issue=url>https://github.com/mamorum/poml/issues
---
{{issue}}
```

**converted**
```
  <issueManagement>
    <url>https://github.com/mamorum/poml/issues</url>
  </issueManagement>
```
