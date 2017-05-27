# dist {{dist}}
```
dist=&ossrh
```

Only `&ossrh` is supported as a val.


## Examples
### Config
**poml**
```
dist=&ossrh
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

### Config + Layout
**poml**
```
dist=&ossrh
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


## References
[Distribution Management and Authentication - The Central Repository](http://central.sonatype.org/pages/apache-maven.html#distribution-management-and-authentication)
