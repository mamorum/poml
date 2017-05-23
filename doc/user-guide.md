# POML - User Guide
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
depend=
  com.google.guava:guava:21.0,
  junit:junit:[4.12\,):test
properties=&encoding>UTF-8, &compiler>1.8
---
{{prj}}&
  {{pkg}}&
  <dependencies>
    <!-- guava, junit -->
    {{depend}}
  </dependencies>&
  {{properties}}
</project>
```

Above file is "[poml/example/demo-layout/pom.poml](../example/demo-layout/pom.poml)".  
It is converted to "[poml/example/demo-layout/pom.pom.xml](../example/demo-layout/pom.xml)".


## 2. Limitation
Poml does not support all XML tags of `pom.xml`.  
To use unsupported XML tags, we can write "Layout Section".


## 3. Config Section
In this section, we can write the `key=val` as a configuration.

```
pkg=com.example:demo:0.0.1:jar
depend=
  com.google.guava:guava:21.0,
  junit:junit:[4.12\,):test
properties=&encoding>UTF-8, &compiler>1.8
```

Available keys (`pkg`, `depend`, etc) are listed in the [Config Reference](../doc/reference.md).  
The val varies according to a key.


### 3.1. Line Endings
If a line ends with `=` or `,`, configuration continues to the next line.  

```
depend=
  com.google.guava:guava:21.0,
  junit:junit:[4.12\,):test
```

In the above case,

- key: `depend`
- val: `com.google.guava:guava:21.0,  junit:junit:[4.12\,):test`

Please do **NOT** ends line with a space,  if it continues.  
Spaces at the end of line are not trimed.


### 3.2. Comma
To use comma as a part of val (not as a separator), we can write escaped comma `\,`.  
For example, to express `[4.12,)` (version 4.12 or over), we can write `[4.12\,)`.

```
  junit:junit:[4.12\,):test
```


## 4. Layout Section (Optional)
In this section, we can write XML elements, placeholders and ampersand.

```
{{model4}}&
  {{pkg}}&
  <dependencies>
    <!-- guava, junit -->
    {{depend}}
  </dependencies>&
  {{properties}}
{{end}}
```

### 4.1. XML Elements
Poml outputs XML elements (and comments) to `poml.xml` as it is.

### 4.2. Placeholders
We can write one placeholder `{{key}}` per one line.  
Poml converts placeholders to XML tags, using `key=val` configurations.

### 4.3. Ampersand
We can add one `&` to the end of line.  
Poml converts `&` to newline.
