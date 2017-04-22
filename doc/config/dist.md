_Since POML v0.2.1_

This converter covers following tags of `pom.xml`.

- /project/distributionManagement
- /project/distributionManagement/snapshotRepository
- /project/distributionManagement/repository


## Example
**poml**
```
dist=snap:ossrh, repo:ossrh
---
{{dist}}
```

**converted**
```
  <distributionManagement>
    <snapshotRepository>
      <id>ossrh</id>
      <url>https://oss.sonatype.org/content/repositories/snapshots</url>
    </snapshotRepository>
    <repository>
      <id>ossrh</id>
      <url>https://oss.sonatype.org/service/local/staging/deploy/maven2/</url>
    </repository>
  </distributionManagement>
```


## Config
```
dist=snap:ossrh, repo:ossrh
```

- **Val**: Map(`k:v, k:v, ... k:v`)

```
# Without repo
dist=snap:ossrh
```

Now, `dist` supports 2 pairs `snap:ossrh` and `repo:ossrh`, to deploy the OSSRH. The details are described in "[Distribution Management and Authentication - The Central Repository](http://central.sonatype.org/pages/apache-maven.html#distribution-management-and-authentication)".
