This converter covers following tag of `pom.xml`.

- /project/dependencies/dependency



## Example
**poml**

```
depend=
  com.github.mamorum:kaze:0.0.1,
  junit:junit:4.12:test
---
  <dependencies>
    <dependency>
      <groupId>com.google.http-client</groupId>
      <artifactId>google-http-client</artifactId>
      <version>1.22.0</version>
      <scope>system</scope>
      <systemPath>${basedir}/lib/google-http-client-1.22.0.jar</systemPath>
    </dependency>
    {{depend}}
  </dependencies>
```

**converted**

```
  <dependencies>
    <dependency>
      <groupId>com.google.http-client</groupId>
      <artifactId>google-http-client</artifactId>
      <version>1.22.0</version>
      <scope>system</scope>
      <systemPath>${basedir}/lib/google-http-client-1.22.0.jar</systemPath>
    </dependency>
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


## Config
Same val as [[depends|depends]].

This converter doesn't output `dependencies` tag.  So, it is possible to add `dependency` tag in layout section, like above example. 
