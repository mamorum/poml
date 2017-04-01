# POML -  User Guide
## Table of Contents
1. Poml File
2. Limitation
3. Config Section
4. Layout Section (Optional)


## 1. Poml File
Poml file named `pom.poml` is converted to `pom.xml`.  
We can write two sections in `pom.poml`.

1. Config Section
2. Layout Section (Optional)

Delimiter `---` separates two sections.  
The delimiter needs newlines on its front and behind.

```
pkg=com.example:demo:0.0.1:jar
depends=
  com.google.guava:guava:21.0,
  junit:junit:[4.12\\,):test
property=$encoding: UTF-8, $compiler: 1.8
fatjar=mainClass: demo.Main
---
{{#model4}}
  {{ pkg }}
  {{depends }}
  {{property }}
  <build>
    <plugins>
      <!-- assembly plugin -->
      {{fatjar}}
    </plugins>
  </build>
{{/model4}}
```

Above file is "[poml/example/demo-layout/pom.poml](https://github.com/mamorum/poml/blob/master/example/demo-layout/pom.poml)",  
and is converted to "[poml/example/demo-layout/pom.pom.xml](https://github.com/mamorum/poml/blob/master/example/demo-layout/pom.xml)".


## 2. Limitation
Poml does not support all XML tags of `pom.xml`.  
To use unsupported XML tags, we can write "Layout Section".


## 3. Config Section
In this section, we can write the `key=val` as a configuration.

```
pkg=com.example:demo:0.0.1:jar
depends=
  com.google.guava:guava:21.0,
  junit:junit:[4.12\\,):test
property=$encoding: UTF-8, $compiler: 1.8
fatjar=mainClass: demo.Main
```

Available keys (`pkg`, `depends`, etc) are listed in the [Config Reference](https://github.com/mamorum/poml/wiki).  
The val varies according to a key.


### 3.1. Line Endings
If a line ends with `=`, `,`or `{`, configuration continues to the next line.  

```
depends=
  com.google.guava:guava:21.0,
  junit:junit:[4.12\\,):test
```

In the above case,

- key: `depends`
- val: `com.google.guava:guava:21.0, junit:junit:4.12:test`

Please do **NOT** ends line with a space ` `.

### 3.2. Comma in a Val
To use comma in a val (not separator), we can write escaped comma `\\,`.  
For example, to use `[4.12,)` (version 4.12 or over), we can write `[4.12\\,)`.

```
  junit:junit:[4.12\\,):test
```


## 4. Layout Section (Optional)
In this section, we can write XML elements and placeholders.

```
{{#model4}}
  {{ pkg }}
  {{depends }}
  {{property }}
  <build>
    <plugins>
      <!-- assembly plugin -->
      {{fatjar}}
    </plugins>
  </build>
{{/model4}}
```

### 4.1. XML Elements
Poml outputs XML elements (and comments) to `poml.xml` as it is.

### 4.2. Placeholders
Poml converts placeholders to XML tags.

We can write one placeholder as `{{key}}` per one line.  
A `key` is same as the one used in "Config Section".
 
#### 4.2.1. Special symbols
Only `model4` starts with `#` or `/`, like `{{#model4}}`.  
Details is in [model4 page](https://github.com/mamorum/poml/wiki/model4).

#### 4.2.2. Spaces
We can add spaces around a `key` like `{{  key  }}`.  
Poml converts one space to one newline.

For example, Poml proceesses `{{ pkg  }}` as following order.

1. converts a space before `pkg` (to a newline) 
2. converts `pkg` (to XML tags)
3. converts 2 spaces after `pkg` (to 2 newlines)
