This converter covers following tags of `pom.xml`.

- /project/name
- /project/description
- /project/inceptionYear
- /project/licenses
- /project/licenses/license


## Example
**poml**
```
info=
  name:POML,
  description:POM's Minimal Language,
  url:https://github.com/mamorum/poml,
  inceptionYear:2016,
  license:MIT
---
{{info}}
```

**converted**
```
  <name>POML</name>
  <description>POM's Minimal Language</description>
  <url>https://github.com/mamorum/poml</url>
  <inceptionYear>2016</inceptionYear>
  <licenses>
    <license>
      <name>MIT License</name>
      <url>http://www.opensource.org/licenses/mit-license.php</url>
    </license>
  </licenses>
```


## Config 
```
info=
  name:v,
  description:v,
  url:v,
  inceptionYear:v,
  license:v
```

- **Val**: Map(`k:v, k:v, ... k:v`)


### license
Now, license supports a single value, Apache 2.0 or MIT. Converted tags are following.

**license:Apache 2.0**
```
  <licenses>
    <license>
      <name>The Apache License, Version 2.0</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
    </license>
  </licenses>
```

**license:MIT**
```
  <licenses>
    <license>
      <name>MIT License</name>
      <url>http://www.opensource.org/licenses/mit-license.php</url>
    </license>
  </licenses>
```

This refers to "[License Information - The Central Repository](http://central.sonatype.org/pages/requirements.html#license-information)".

