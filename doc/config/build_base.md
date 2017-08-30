# build.base {{build.base}}
```
build.base=defaultGoal>v, finalName>v
```


## Examples
### Config
**poml**
```
build.base=
  defaultGoal>test, finalName>demo
```

**converted**
```
    <defaultGoal>test</defaultGoal>
    <finalName>demo</finalName>
```

### Config + Layout
**poml**
```
build.base=finalName>demo
---
  <build>
    {{build.base}}
  </build>
```


**converted**
```
  <build>
    <finalName>demo</finalName>
  </build>
```
