# POML -  User Guide
## Table of Contents
- Getting Started
    - Install
    - Example
- Overview
- Poml File
    - Overview
    - Config Section
    - Layout Section
- Poml Converters
    - Project Model
        - model4
    - Basics
        - dist
        - depends
        - property
    - Build Plugins
        - compiler
        - exec
        - fatjar
    - More Project Infomation
        - info


## Getting Started
### Install
[Installation Guide](./installation-guide.md) describes how to install.

### Example
In the 'Example' section of [Readme](../readme.md), Poml converts a poml file to `pom.xml`.


## Overview
This guide describes the usage of Poml, especially about the 'Poml File' (`pom.poml`) and 'Poml Converters'. Both are needed to generate a `pom.xml`.

One 'Poml File' is created per a `pom.xml`. 'Poml Converters' are in the installed resources with poml command (`> poml`).


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
...


### Layout Section
...
