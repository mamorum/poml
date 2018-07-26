# POML: POM's Minimal Language
There are two things in Poml.

1. Syntax for writing Poml file named `pom.poml`.
2. Conversion tool from `pom.poml` to [Maven](https://maven.apache.org/) `pom.xml`.


## Documents
- [Releases (Download)](https://github.com/mamorum/poml/releases)
- [Installation Guide](doc/installation-guide.md)
- [User Guide](doc/user-guide.md)
- [Reference](doc/reference.md)


## Getting Started
### 1. Create Poml File
Create a `pom.poml` and save the following text. (ex. in the `demo` directory.)

```txt
pkg=com.example:demo:0.0.1:jar
depend=
  org.slf4j:slf4j-api:1.7.25,
  junit:junit:4.12:test
properties=&encoding>UTF-8, &compiler>1.8
```


### 2. Execute Poml Command
Execute `poml`. ( After [Installing Poml](doc/installation-guide.md). )

```
demo$ poml
[INFO] Converting pom.poml
[INFO] Created pom.xml @30ms
```

### 3. Check Pom XML
Following `pom.xml` is created.

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.example</groupId>
  <artifactId>demo</artifactId>
  <version>0.0.1</version>
  <packaging>jar</packaging>

  <dependencies>
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-api</artifactId>
      <version>1.7.25</version>
    </dependency>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>
  </dependencies>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
  </properties>
</project>
```

Above `pom.poml` and `pom.xml` are in [example/demo](example/demo).


## Example
In the following directories, `pom.poml` and converted `pom.xml` exist.

- [example/all](example/all): Using all configs of Poml
- [example/all-layout](example/all-layout): Using all configs and "Layout Section"
- [example/demo-layout](example/demo-layout): Using "Layout Section"


## Showcase
- [poml](https://github.com/mamorum/poml): eats own poml.
- [kaze](https://github.com/mamorum/kaze): java framework, deployed to the maven central repository.
- [kaze-sample-rdb](https://github.com/mamorum/kaze-sample/tree/master/rdb): kaze web app, packaged as fatjar.


## Versioning
Number is `MAJOR.MINOR.PATCH`, based on [Semantic Versioning 2.0.0](http://semver.org/).

Increment the:

1. `MAJOR` for incompatible changes on poml syntax or command,
2. `MINOR` for backwards-compatible changes on those, and
3. `PATCH` for backwards-compatible bug fixes.


## Build Status
- [Travis CI](https://travis-ci.org/mamorum/poml)
