# POML -  User Guide
## Table of Contents
- Getting Started
- Poml Overview
- Poml File
    - Overview
    - Abbreviation
    - Config Section
    - Layout Section
- Poml Converters
    - Overview
    - Reference


## Getting Started
- **Install**: [Installation Guide](./installation-guide.md)
- **Example**: [Readme](../readme.md)


## Poml Overview
Poml Processor generates a `pom.xml`, using Poml File (`pom.poml`) and Converters.

This document describes Poml File and Converters.


## Poml File
### Overview
Poml File consists of two parts. First part is "Config Section". Second part is "Layout Section". 

```
pkg=com.example:demo:0.0.1:jar
depends=
  junit:junit:4.12:test,
  org.assertj:assertj-core:3.2.0:test
---
{{#model4}}
  {{pkg}}
  <name>Demo</name>
{{#model4}}
```

The delimiter `---` separates two sections. And it needs newlines on its front and behind.


### Abbreviation
Poml supports abbreviation of it's file. In this case, Poml File consists of "Config Section" only. 

```
pkg=com.example:demo:0.0.1:jar
depends=
  junit:junit:4.12:test,
  org.assertj:assertj-core:3.2.0:test
```

Poml determines XML layout automatically.


### Config Section
In this section, we can write a configuration as `key=value`. If a configuration line ends with `=`, `,`or `{`, it continues to the next line.

```
depends=
  junit:junit:[4.12\\,):test,
  org.assertj:assertj-core:3.2.0:test
```

The `key` is a converter name (ex.`pkg`,`depends`, etc).

The `value` varies according to a converter. The `value` sometimes contains comma `,` as a separator. To use comma as a value of XML, we can write escaped `\\,`. For example, to express the version `[4.12,)` (4.12 or over in maven), we can write `[4.12\\,)`.




### Layout Section
Poml outputs this section to `pom.xml`, converting placeholders. A placeholder is expressed as `{{key}}`, and `key` is a converter name. Depending on the converter, placeholder contains specific symbols (ex.`#`,`/`).

```
{{#model4}}
  {{pkg}}
  <name>Demo App</name>
{{/model4}}
```

We can add spaces around the `key` like `{{ pkg }}`. Poml converts the front spaces to newlines before converting placeholders and the behind spaces after converting. One space is converted one newline.

Now, we can write one pleceholder per one line. 


## Poml Converters
### Overview
Poml Converter generates XML elements, using it's configuration.

### Reference
Poml Converters are listed in [Converter Reference](https://github.com/mamorum/poml/wiki). It describes converter configurations, examples and so on.
