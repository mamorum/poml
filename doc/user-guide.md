# POML -  User Guide
## Table of Contents
- Getting Started
    - Install
    - Example
- Poml Overview
- Poml File
    - Overview
    - Config Section
    - Layout Section
- Poml Converters
    - Overview
    - Reference


## Getting Started
### Install
[Installation Guide](./installation-guide.md) describes how to install.

### Example
In the 'Example' section of [Readme](../readme.md), Poml converts a poml file to `pom.xml`.


## Poml Overview
Poml generates a `pom.xml`, reading a 'Poml File' and calling  'Poml Converters'.


## Poml File
### Overview
Poml file consists of two parts. First part is a 'Config Section', in which we write configurations for converters. Second part is a 'Layout Section', in which we write placeholders and XML elements.

These two sections are separated by the delimiter `---`, like following.

```
dist=com.example:demo:0.0.1:jar
---
{{#model4}}
  {{dist}}
  <name>Demo</name>
{{#model4}}
```

The delimiter needs new lines on its front and behind.


### Config Section
The syntax of this section is based on Java's property file.

A configuration is defined as `key=value`. A `key` is a converter name, and `value` varies according to a converter. 

```
dist=com.example:demo:0.0.1:jar
depends=
  junit:junit:4.12:test,
  org.assertj:assertj-core:3.2.0:test
```

Backslash `\` is not needed to escape newline, if a line ends with `=`, `:`, or `,`. In this case, Poml considers that the configuration continues to next line.


### Layout Section
The syntax of this section is based on XML.

Writing placeholders are available to generate XML defined `{{key}}` 

```
  {{dist}}
  <name>Demo</name>
```

If a placehoder has spaces at the front and back of the key like `{{ key }}`, Poml convert space to newline.


## Poml Converters
### Overview
...

### Reference
Poml Converters are listed in [Converter Reference](/mamorum/poml/wiki). It describes configuration values, sample usages and so on.
