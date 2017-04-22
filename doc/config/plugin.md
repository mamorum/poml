This converter covers following tag of `pom.xml`.

- /project/build/plugins/plugin


## Example
**poml**
```
fatjar=
  ver:2.6, 
  jarName:${project.artifactId},
  mainClass:poml.Main
---
{{fatjar}}
```

**converted**
```
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
          </archive>
        </configuration>
        <executions>
          <execution>
            <id>make-assembly</id>
            <phase>package</phase>
            <goals><goal>single</goal></goals>
          </execution>
        </executions>
      </plugin>
```


## Config 
```
fatjar=ver:v, jarName:v, mainClass:v
```

- **Val**: Map(`k:v, k:v, ... k:v`)
- **Required**: mainClass
- **Optional**:
    - ver: default is `2.6` (plugin version)
    - jarName: default is `${project.artifactId}` (fatjar name)

```
# Without ver and jarName
fatjar=mainClass:v
```
