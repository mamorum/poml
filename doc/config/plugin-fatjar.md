# plugin=&fatjar
```
plugin=&fatjar
&fatjar=mainClass:v, ver:v, jarName:v
&fatjar.conf+={
  <key>val</key>
  ...
}
&fatjar.conf.archive+={
  <key>
    <key>val...
}
```

- **Required**: mainClass
- **Optional**:
    - ver: version of maven-assembly-plugin (default is `2.6`)
    - jarName: fatjar name (default is `${project.artifactId}`)
    - conf+: tags added to `plugin/configuration`
    - conf.archive+: tags added to `plugin/configuration/archive`


## Examples
### Config
**poml**
```
plugin=&fatjar
&fatjar=mainClass>poml.Main
&fatjar.conf+={
  <outputDirectory>dist</outputDirectory>
}
&fatjar.conf.archive+={
  <manifestEntries>
    <Implementation-Version>${project.version}</Implementation-Version>
    <Built-By>Poml Authors</Built-By>
  </manifestEntries>
}
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
          <archive>
            <manifest>
              <mainClass>poml.Main</mainClass>
            </manifest>
            <manifestEntries>
              <Implementation-Version>${project.version}</Implementation-Version>
              <Built-By>Poml Authors</Built-By>
            </manifestEntries>
          </archive>
          <outputDirectory>dist</outputDirectory>
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
