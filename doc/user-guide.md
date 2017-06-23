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
  org.slf4j:slf4j-api:1.7.25,
  junit:junit:[4.12\,):test
properties=&encoding>UTF-8, &compiler>1.8
plugin=$compiler
$compiler=org.apache.maven.plugins:maven-compiler-plugin:3.6.1
$compiler.conf={
  <compilerArgs>
    <arg>-verbose</arg>
  </compilerArgs>
}
---
{{prj}}&
  {{pkg}}&
  <dependencies>
    {{depend}}
  </dependencies>&
  {{properties}}&
  <build>
    <plugins>
      <!-- compiler -->
      {{plugin}}
    </plugins>
  </build>
{{/prj}}
```

Above file is "[poml/example/demo-layout/pom.poml](../example/demo-layout/pom.poml)".  
It is converted to "[poml/example/demo-layout/pom.pom.xml](../example/demo-layout/pom.xml)".


## 2. Limitation
### 2.1. XML tags
Poml does not support all XML tags of `pom.xml`.  
To use unsupported XML tags, we can write "Layout Section".

### 2.2. Validation
Validation is implemented only a little in Poml.  
Because it is implemented in Maven.


## 3. Config Section
In this section, we can write the `key=val` as a configuration.

```
pkg=com.example:demo:0.0.1:jar
depend=
  org.slf4j:slf4j-api:1.7.25,
  junit:junit:[4.12\,):test
properties=&encoding>UTF-8, &compiler>1.8
plugin=$compiler
$compiler=org.apache.maven.plugins:maven-compiler-plugin:3.6.1
$compiler.conf={
  <compilerArgs>
    <arg>-verbose</arg>
  </compilerArgs>
}
```

Available keys (`pkg`, `depend`, etc) are listed in the [Config Reference](../doc/reference.md).  
The val varies according to a key.


### 3.1. Line Endings
If a line ends with `key=` or `,`, configuration continues to the next line.  

```
depend=
  org.slf4j:slf4j-api:1.7.25,
  junit:junit:[4.12\,):test
```

In the above case,

- key: `depend`
- val: `org.slf4j:slf4j-api:1.7.25,  junit:junit:[4.12\,):test`

Please do **NOT** ends line with a space,  if it continues.  
Spaces at the end of line are not trimed.


### 3.2. Comma
To use comma as a part of val (not as a separator), we can write escaped comma `\,`.  
For example, to express `[4.12,)` (version 4.12 or over), we can write `[4.12\,)`.

```
  junit:junit:[4.12\,):test
```


### 3.3. XML
Depending on the key, we sometimes write XML as a val.  

```
$compiler.conf={
  <compilerArgs>
    <arg>-verbose</arg>
  </compilerArgs>
}
```

Like above, XML val must be surronded with `key={`+`newline` and `newline`+`}`.

And adding 2 spaces for indents is recommended.  
Poml outputs tags and indents to `pom.xml`.


## 4. Layout Section (Optional)
In this section, we can write XML elements, placeholders and ampersand.

```
{{prj}}&
  {{pkg}}&
  <dependencies>
    {{depend}}
  </dependencies>&
  {{properties}}&
  <build>
    <plugins>
      <!-- compiler -->
      {{plugin}}
    </plugins>
  </build>
{{/prj}}
```

### 4.1. XML Elements
Poml outputs XML elements (and comments) to `poml.xml` as it is.

### 4.2. Placeholders
We can write one placeholder `{{key}}` per one line.  
Poml converts placeholders to XML tags, using `key=val` configurations.

### 4.3. Ampersand
We can write one `&` at the end of line, for adding blank line to XML.  
Poml converts `&` to newline. 
