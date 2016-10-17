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
"Example Section" of [Readme](../readme.md) describes how Poml works.


## Poml Overview
"Poml Processor" generates a Maven `pom.xml`, reading a "Poml File (`pom.poml`)" and calling  "Poml Converters".


## Poml File
### Overview
Poml File consists of two parts. First part is "Config Section". Second part is "Layout Section". 

```
dist=com.example:demo:0.0.1:jar
depends=
  junit:junit:4.12:test,
  org.assertj:assertj-core:3.2.0:test
---
{{#model4}}
  {{dist}}
  <name>Demo</name>
{{#model4}}
```

The delimiter `---` separates two sections. And it needs newlines on its front and behind.

### Config Section
In this section, we can write the configuration as `key=value`. The `key` is a converter name (ex.`dist`,`depends`). The `value` varies according to a converter. 

```
dist=com.example:demo:0.0.1:jar
```


```
depends=
  junit:junit:[4.12&comma;):test,
  org.assertj:assertj-core:3.2.0:test
```

And, if a line ends with `=` or `,`, Poml Processor considers that the configuration (`key=value`) continues to next line.

### Layout Section
Poml Processor outputs this section to `pom.xml`, converting placeholders. The placeholder is expressed as `{{key}}`. The `key` is a converter name. Depending on the converter, placeholder contains specific symbols (ex.`#`,`/`).

```
{{#model4}}
  {{dist}}
  <name>Demo App</name>
{{/model4}}
```

We can add spaces around the `key` like `{{ dist }}`. Poml Processor converts the front spaces to newlines before converting placeholders and the behind spaces after converting. One space is converted one newline.

Now, we can write one pleceholder per one line. 


## Poml Converters
### Overview
Poml Converter is called when Poml Processor meets a placeholder in the layout section. Converter gets configuration from config section and generates the elements of `pom.xml`.

### Reference
Poml Converters are listed in [Converter Reference](https://github.com/mamorum/poml/wiki). It describes converter configurations, examples and so on.
