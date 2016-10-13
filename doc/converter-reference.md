# POML -  Converter Reference
## Table of Contents
- Overview
- Project Model
    - model4
- Basics
    - 1.dist
    - 2.depends
    - 3.depend
    - 4.property
- Build Plugins
    - compiler
    - exec
    - fatjar
- More Project Infomation
    - info


## Overview
...

## Project Model
### 1. model4
`{{#model4}}` `{{/model4}}`

#### 1.1. config
Config is not needed.


## Basics
### 1. dist
#### 1.1. config 
A project config values are separated by `:`.

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
Dependency config values are separated by `:`. And dependency separated by `,`.

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

### 4. property
