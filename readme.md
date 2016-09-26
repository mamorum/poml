# POML
[![Build Status](https://travis-ci.org/mamorum/poml.svg?branch=master)](https://travis-ci.org/mamorum/poml)

POM (pom.xml) 's Obvious, Minimal Language.

Poml is a conversion tool from text to [maven](https://maven.apache.org/) pom.xml. There are two things in poml.

1. Text syntax for writing `pom.poml` (poml file).
2. Processor for converting `pom.poml` to `pom.xml`.


## Guides
[Getting Started](doc/getting-started.md)


## Example
Like following, `pom.poml` is converted to `pom.xml`.

`pom.poml`

```txt
dist=com.example:demo:0.0.1:jar
lib=\
  junit:junit:4.12:test,\
  org.assertj:assertj-core:3.2.0:test
prop=project.build.sourceEncoding:UTF-8
javac=source:1.8, target:1.8
---
{{#model4}}
  {{dist}}
  {{lib}}
  {{prop}}
  <build>
    <plugins>
      {{javac}}
    </plugins>
  </build>
{{/model4}}
```

`pom.xml`

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
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.5.1</version>
        <configuration>
          <source>1.8</source>
          <target>1.8</target>
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>
```


## Showcase
POML eats its own poml.

In this project, [pom.poml](pom.poml) is converted to [pom.xml](pom.xml).
