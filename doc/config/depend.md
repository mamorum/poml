# depend {{depend}}
```
depend=
  groupId:artifactId:version:scope:optional:type,
  groupId:artifactId::::type,
  groupId:artifactId
```


## Examples
### Config
**poml**

```
depend=
  com.example:artifact1:0.0.1:test:true:jar,
  com.example:artifact2::::jar,
  com.example:artifact3
```

**converted**

```
  <dependencies>
    <dependency>
      <groupId>com.example</groupId>
      <artifactId>artifact1</artifactId>
      <version>0.0.1</version>
      <scope>test</scope>
      <optional>true</optional>
      <type>jar</type>
    </dependency>
    <dependency>
      <groupId>com.example</groupId>
      <artifactId>artifact2</artifactId>
      <type>jar</type>
    </dependency>
    <dependency>
      <groupId>com.example</groupId>
      <artifactId>artifact3</artifactId>
    </dependency>
  </dependencies>
```


### Config + Layout
**poml**

```
depend=junit:junit:[4.12\,):test
---
  <dependencies>
    {{depend}}
    <dependency>
      <groupId>com.google.http-client</groupId>
      <artifactId>google-http-client</artifactId>
      <version>1.22.0</version>
      <scope>system</scope>
      <systemPath>${basedir}/lib/google-http-client-1.22.0.jar</systemPath>
    </dependency>
  </dependencies>
```

**converted**

```
  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>[4.12,)</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>com.google.http-client</groupId>
      <artifactId>google-http-client</artifactId>
      <version>1.22.0</version>
      <scope>system</scope>
      <systemPath>${basedir}/lib/google-http-client-1.22.0.jar</systemPath>
    </dependency>
  </dependencies>
```
