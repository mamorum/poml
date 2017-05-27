# license {{license}}
```
license=$1, $2, ...
$1=name>v, url>v, distribution>v, comments>v
$2=name>v, url>v, distribution>v, comments>v
...
```

- **Optional**: name, url, distribution, comments


## Embedded License
- `&apache2`
- `&mit`


## Examples
### Config
**poml**
```
license=&apache2, &mit, $bsd2
$bsd2=
  name>The New BSD License,
  url>http://www.opensource.org/licenses/bsd-license.php,
  distribution>repo, comments>The 2-Clause BSD License
```

**converted**
```
  <licenses>
    <license>
      <name>The Apache License, Version 2.0</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
    </license>
    <license>
      <name>MIT License</name>
      <url>https://opensource.org/licenses/MIT</url>
    </license>
    <license>
      <name>The New BSD License</name>
      <url>http://www.opensource.org/licenses/bsd-license.php</url>
      <distribution>repo</distribution>
      <comments>The 2-Clause BSD License</comments>
    </license>
  </licenses>
```

### Config + Layout
**poml**
```
license=$wtfpl
$wtfpl=name>WTFPL, url>http://www.wtfpl.net
---
  <licenses>
    {{license}}
    <license>
      <name>The New BSD License</name>
      <url>http://www.opensource.org/licenses/bsd-license.php</url>
    </license>    
  </licenses>
```

**converted**
```
  <licenses>
    <license>
      <name>WTFPL</name>
      <url>http://www.wtfpl.net</url>
    </license>
    <license>
      <name>The New BSD License</name>
      <url>http://www.opensource.org/licenses/bsd-license.php</url>
    </license>
  </licenses>
```


## References
[License Information - The Central Repository](http://central.sonatype.org/pages/requirements.html#license-information)
