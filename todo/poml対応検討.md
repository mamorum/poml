## Poml
### 優先度：低
#### カテゴリコンバート（レイアウトありの場合）
{{basic}}, {{env}}, {{more}} などに対応する

#### バリデーション
- pom.poml に不正なkeyがあっても処理が成功する、等々。

#### 検討：depend (dependencies) の exclusions タグ対応
```
depend=
  com.example:artifact1:0.0.1:test:true:jar:$1,
  com.example:artifact2::::jar:$1,
  com.example:artifact3:::::$1

$1.exclude=
  javax.servlet:servlet-api, 
  javax.servlet:servlet-api

com.example:artifact1=
  exclude>javax.servlet:servlet-api, javax.servlet:servlet-api 
```

```
depend=
  com.example:artifact1:0.0.1:test:true:jar:!{javax.servlet:servlet-api, javax.servlet:servlet-api},
  com.example:artifact2::::jar:!{javax.servlet:servlet-api, javax.servlet:servlet-api},
  com.example:artifact3:::::javax.servlet:servlet-api, javax.servlet:servlet-api
```
