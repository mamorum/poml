# plugin=&fatjar
```
plugin=&fatjar
&fatjar=version>v, finalName>v, mainClass>v
&fatjar.conf+={
  <key>val</key>
  ...
}
&fatjar.conf.archive+={
  <key>
    <key>val...
}
```

- version: version of maven-assembly-plugin (default is `2.6`)
- finalName: fatjar name (default is `${project.artifactId}`)
- mainClass: value of manifest `Main-Class` (default is none)
- conf+: markup added to `plugin/configuration`
- conf.archive+: markup added to `plugin/configuration/archive`


## Examples
### Config
**poml**
```
plugin=&fatjar
```

**converted**
```
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-assembly-plugin</artifactId>
        <version>2.6</version>
        <configuration>
          <finalName>${project.artifactId}</finalName>
          <descriptorRefs>
            <descriptorRef>jar-with-dependencies</descriptorRef>
          </descriptorRefs>
          <appendAssemblyId>false</appendAssemblyId>
          <attach>false</attach>
        </configuration>
        <executions>
          <execution>
            <id>make-assembly</id>
            <phase>package</phase>
            <goals><goal>single</goal></goals>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
```

### Config + Layout
**poml**
```
plugin=&fatjar
&fatjar=
  version>3.0.0, finalName>poml, mainClass>poml.Main
&fatjar.conf+={
  <outputDirectory>dist</outputDirectory>
}
&fatjar.conf.archive+={
  <manifestEntries>
    <Implementation-Version>${project.version}</Implementation-Version>
    <Built-By>Poml Authors</Built-By>
  </manifestEntries>
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
        <artifactId>maven-assembly-plugin</artifactId>
        <version>3.0.0</version>
        <configuration>
          <finalName>poml</finalName>
          <outputDirectory>dist</outputDirectory>
          <archive>
            <manifest>
              <mainClass>poml.Main</mainClass>
            </manifest>
            <manifestEntries>
              <Implementation-Version>${project.version}</Implementation-Version>
              <Built-By>Poml Authors</Built-By>
            </manifestEntries>
          </archive>
          <descriptorRefs>
            <descriptorRef>jar-with-dependencies</descriptorRef>
          </descriptorRefs>
          <appendAssemblyId>false</appendAssemblyId>
          <attach>false</attach>
        </configuration>
        <executions>
          <execution>
            <id>make-assembly</id>
            <phase>package</phase>
            <goals><goal>single</goal></goals>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
```
