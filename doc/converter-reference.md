# POML -  Converter Reference
## Table of Contents
- Overview
- Project Model
    - 1.model4
- Basics
    - 1.dist
    - 2.depends
    - 3.depend
    - 4.property
- Build Plugins
    - 1.compiler
    - 2.exec
    - 3.fatjar
- More Project Infomation
    - 1.info


## Overview
...

## Project Model
### 1. model4
This converter needs no config.

Placeholders `{{#model4}}` and `{{/model4}}` in the layout section are converted to XML tags.

##### src
```
---
{{#model4}}
  ...
{{/model4}}
```

##### converted
```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  ...
</project>
```


## Basics
### 1. dist
#### 1.1. config 
Set project configurations separated by `:`.

```
dist=groupId:artifactId:version:packaging
```

#### 1.2. example
##### src
```
dist=com.example:demo:0.0.1:jar
---
{{dist}}
```

##### converted
```
  <groupId>com.example</groupId>
  <artifactId>demo</artifactId>
  <version>0.0.1</version>
  <packaging>jar</packaging>
```


### 2. depends
#### 2.1. config 
Set dependency configurations separated by `:`. Dependencies are separated by `,`.

```
depends=
  groupId:artifactId:version:scope:optional:type,
  groupId:artifactId:version:scope:optional:type
```

#### 2.2. example
##### src
```
depends=
  com.github.mamorum:kaze:0.0.1,
  junit:junit:4.12:test
---
{{depends}}
```

##### converted
```
  <dependencies>
    <dependency>
      <groupId>com.github.mamorum</groupId>
      <artifactId>kaze</artifactId>
      <version>0.0.1</version>
    </dependency>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
```

### 3. depend
#### 3.1. config 
Set same configurations as `depends`. This converter doesn't output `dependencies` tag.

#### 3.2. example
##### src
```
depend=
  com.github.mamorum:kaze:0.0.1,
  junit:junit:4.12:test
---
{{depend}}
```

##### converted
```
    <dependency>
      <groupId>com.github.mamorum</groupId>
      <artifactId>kaze</artifactId>
      <version>0.0.1</version>
    </dependency>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>
```



### 4. property
#### 4.1. config 
Set property key and value separated by `:`. Properties are separated by `,`.

```
property=
  key1:value1,
  key2:value2
```

#### 4.2. example
##### src
```
property=project.build.sourceEncoding:UTF-8
---
{{property}}
```

##### converted
```
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>
```


## Build Plugins
### 1. compiler

### 2. exec

### 3. fatjar


## More Project Infomation
### 1.info

