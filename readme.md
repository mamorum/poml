# POML: POM's Minimal Language
[![Build Status](https://travis-ci.org/mamorum/poml.svg?branch=master)](https://travis-ci.org/mamorum/poml)

Poml is a conversion tool from text to [Maven](https://maven.apache.org/) `pom.xml`. There are two things in poml.

1. Text syntax for writing poml file named `pom.poml`.
2. Processor converting `pom.poml` to `pom.xml`.


## Documents
- [Installation Guide](doc/installation-guide.md)
- [User Guide](doc/user-guide.md)
- [Converter Reference](https://github.com/mamorum/poml/wiki)
- [Blog](http://java-poml.tumblr.com/)


## Example
### 1. Create Poml File
Create a `pom.poml` and save the following text. (ex. in the `demo` directory.)

```txt
pkg=com.example:demo:0.0.1:jar
depends=
  junit:junit:4.12:test,
  org.assertj:assertj-core:3.2.0:test
property=project.build.sourceEncoding:UTF-8
```


### 2. Execute Poml Command
Execute `poml` command. ([Installation Guide](doc/installation-guide.md))

```
demo$ poml
```

### 3. Check XML
Poml generates following `pom.xml`.

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.example</groupId>
  <artifactId>demo</artifactId>
  <version>0.0.1</version>
  <packaging>jar</packaging>

  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.assertj</groupId>
      <artifactId>assertj-core</artifactId>
      <version>3.2.0</version>
      <scope>test</scope>
    </dependency>
  </dependencies>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>
</project>
```

### Note: With Maven Command
Combined command like `poml && mvn test` is convenient to execute Maven with successfully generated `pom.xml`. 

```
demo$ poml && mvn <goal>
```

Operater `&&` seems available also in the Windows (see [Command shell overview - MS TechNet](https://technet.microsoft.com/en-us/library/bb490954.aspx)).


## More Examples
There are some examples in the sub directories of [example](example).

- [demo](example/demo): Same as above `demo` example.
- [demo-layout](example/demo-layout): Same configs as `demo`, but using "Layout Section".
- [all](example/all): Using all implemented configs ([converters](https://github.com/mamorum/poml/wiki)).
- [all-layout](example/all-layout): Using all configs and "Layout Section".

Each sub directories have two files, `poml.poml` and `pom.xml`. XML generated from `pom.poml` is same as `pom.xml`. Test case [IntegrationTest.java](src/test/java/it/IntegrationTest.java) checks it continuously.


## Showcase
POML eats its own poml.

In this project, [pom.poml](pom.poml) is converted to [pom.xml](pom.xml).
