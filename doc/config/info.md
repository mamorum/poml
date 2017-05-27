# info {{info}}
```
info=name>v, description>v, url>v, inceptionYear>v
```

- **Optional**: name, description, url, inceptionYear


## Examples
### Config
**poml**
```
info=
  name>POML, description>POM's Minimal Language,
  url>https://github.com/mamorum/poml
```

**converted**
```
  <name>POML</name>
  <description>POM's Minimal Language</description>
  <url>https://github.com/mamorum/poml</url>
```

### Config + Layout
**poml**
```
info=
  name>POML, description>POM's Minimal Language,
  url>https://github.com/mamorum/poml, inceptionYear>2016
---
{{info}}
```

**converted**
```
  <name>POML</name>
  <description>POM's Minimal Language</description>
  <url>https://github.com/mamorum/poml</url>
  <inceptionYear>2016</inceptionYear>
```