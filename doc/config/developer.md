# developer {{developer}}
```
developer=$1, $2, ...
$1=id>v, name>v, email>v, url>v
$2=id>v, name>v, email>v, url>v
```

- **Optional**: id, name, email, url


## Examples
### Config
**poml**
```
developer=$jdoe, $ken
$jdoe=
  id>jdoe, name>John Doe,
  email>jdoe@example.com,
  url>http://www.example.com/jdoe
$ken=id>ken
```

**converted**
```
  <developers>
    <developer>
      <id>jdoe</id>
      <name>John Doe</name>
      <email>jdoe@example.com</email>
      <url>http://www.example.com/jdoe</url>
    </developer>
    <developer>
      <id>ken</id>
    </developer>
  </developers>
```

### Config + Layout
**poml**
```
developer=$ken
$ken=id>ken
---
  <developers>
    {{developer}}
    <developer>
      <id>jdoe</id>
      <organization>ACME</organization>
      <organizationUrl>http://www.example.com</organizationUrl>
    </developer>
  </developers>
```

**converted**
```
  <developers>
    <developer>
      <id>ken</id>
    </developer>
    <developer>
      <id>jdoe</id>
      <organization>ACME</organization>
      <organizationUrl>http://www.example.com</organizationUrl>
    </developer>
  </developers>
```
