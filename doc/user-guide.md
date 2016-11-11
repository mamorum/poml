# POML -  User Guide
## Table of Contents
1. Getting Started
2. How Poml Works
3. Poml File
    - Overview
    - Abbreviation
    - Config Section
    - Layout Section
4. Poml Converter
    - Overview
    - Reference


## 1. Getting Started
- **Install**: [Installation Guide](./installation-guide.md)
- **Example**: [Readme](../readme.md)


## 2. How Poml Works
Poml works in the following order, after `poml` command is executed.

1. Poml Processor reads Poml File.
2. Processor invokes Poml Converters.
3. Converters generate POM (XML) elements.

To generate a `pom.xml`, Poml File and Converters are needed.  
So, this document describes Poml File and Converter.


## 3. Poml File
### 3.1. Overview
Poml File consists of two parts. First part is "Config Section". Second part is "Layout Section". 

```
pkg=com.example:demo:0.0.1:jar
depends=
  junit:junit:4.12:test,
  org.assertj:assertj-core:3.2.0:test
compiler=source:1.8, target:1.8
---
{{#model4}}
  {{ pkg }}
  {{depends }}
  <build>
    <plugins>
      {{compiler}}
    </plugins>
  </build>
{{/model4}}
```

The delimiter `---` separates two sections. And it needs newlines on its front and behind.


### 3.2. Abbreviation
Poml supports abbreviation of it's file. In this case, Poml File consists of "Config Section" only. 

```
pkg=com.example:demo:0.0.1:jar
depends=
  junit:junit:4.12:test,
  org.assertj:assertj-core:3.2.0:test
compiler=source:1.8, target:1.8
```

Poml determines XML layout automatically.


### 3.3. Config Section
In this section, we can write a configuration as `key=val`.

#### 3.3.1. Key
The `key` is a converter name (ex.`pkg`). In the [Converter Reference](https://github.com/mamorum/poml/wiki), the names are listed.

#### 3.3.2. Val
The `val` varies according to a converter. There are `String`, `Array` and `Map` as its pattern.

**String: `v`**
```
pkg=com.example:demo:0.0.1:jar
```

**Array: `v, v, ... v`**
```
depends=junit:junit:4.12:test, org.assertj:assertj-core:3.2.0:test
```

**Map: `k:v, k:v, ... k:v`**
```
compiler=source:1.8, target:1.8
```


#### 3.3.3. Line Endings
If a line ends with `=`, `,`or `{`, configuration continues to the next line.

```
depends=
  junit:junit:4.12:test,
  org.assertj:assertj-core:3.2.0:test
```

Please do **NOT** ends line with a space ` `.

#### 3.3.4. Comma
To use comma as a value of XML (not separator in `val`), we can write escaped `\\,`.  
For example, to express the version `[4.12,)` (4.12 or over), we can write `[4.12\\,)`.

```
depends=junit:junit:[4.12\\,):test
```


### 3.4. Layout Section
Poml outputs this section to `pom.xml`, converting placeholders. A placeholder is expressed as `{{key}}`, and `key` is a converter name. Depending on the converter, placeholder contains specific symbols (ex.`#`,`/`).

```
{{#model4}}
  {{ pkg }}
  <build>
    <plugins>
      {{compiler}}
    </plugins>
  </build>
{{/model4}}
```

We can add spaces around the `key` like `{{ pkg }}`. Poml converts the front spaces to newlines before converting placeholders and the behind spaces after converting. One space is converted one newline.

Now, we can write one pleceholder per one line. 


## 4. Poml Converter
### 4.1. Overview
Converter generates POM (XML) elements, using it's configuration.

### 4.2. Reference
Converters are listed in [Converter Reference](https://github.com/mamorum/poml/wiki). It describes converter configurations, examples and so on.
