# plugin {{plugin}}
```
plugin=$1, $2, ...
$1=groupId:artifactId:version:extensions:inherited
$1.conf={
  <key>val</key>
  <key>...
}
$1.depends={
  <key>
    <key>...
}
$1.execs={
  <key />
  ...
}
$2=...
```

## Embedded Plugin
- [&fatjar](./plugin-fatjar.md)
- [&ossrh](./plugin-ossrh.md)


## Examples
### Config
**poml**
```
plugin=$demo, $compiler
$demo=org.demo:demo-plugin
$compiler=:maven-compiler-plugin:3.6.1
$compiler.conf={
  <fork>true</fork>
}
```

**converted**
```
  <build>
    <plugins>
      <plugin>
        <groupId>org.demo</groupId>
        <artifactId>demo-plugin</artifactId>
      </plugin>
      <plugin>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.6.1</version>
        <configuration>
          <fork>true</fork>
        </configuration>
      </plugin>
    </plugins>
  </build>
```


### Config + Layout
**poml**
```
plugin=$jar
$jar=org.apache.maven.plugins:maven-jar-plugin:2.6:false:true
$jar.conf={
  <classifier>test</classifier>
}
$jar.depends={
  <dependency>
    <groupId>org.apache.ant</groupId>
    <artifactId>ant</artifactId>
    <version>1.7.1</version>
  </dependency>
}
$jar.execs={
  <execution>
    <id>execution1</id>
    <phase>jar</phase>
  </execution>
}
---
  <build>
    <plugins>
      {{plugin}}
    </plugins>
  </build>
```

**converted**
```
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-jar-plugin</artifactId>
        <version>2.6</version>
        <extensions>false</extensions>
        <inherited>true</inherited>
        <configuration>
          <classifier>test</classifier>
        </configuration>
        <dependencies>
          <dependency>
            <groupId>org.apache.ant</groupId>
            <artifactId>ant</artifactId>
            <version>1.7.1</version>
          </dependency>
        </dependencies>
        <executions>
          <execution>
            <id>execution1</id>
            <phase>jar</phase>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
```
