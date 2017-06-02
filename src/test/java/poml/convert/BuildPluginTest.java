package poml.convert;

import org.junit.Test;

// $
public class BuildPluginTest extends TestCase {
  @Test public void grp_art() {
    poml(
      "plugin=$sbp" + nl +
      "$sbp=org.springframework.boot:spring-boot-maven-plugin"
    );
    Build.plugin(poml, xml);
    xml(
      "      <plugin>" + nl +
      "        <groupId>org.springframework.boot</groupId>" + nl +
      "        <artifactId>spring-boot-maven-plugin</artifactId>" + nl +
      "      </plugin>" + nl
    );
  }

  @Test public void grp_exec() {
    poml(
      "plugin=$jar" + nl +
      "$jar=org.apache.maven.plugins:maven-jar-plugin:2.6:false:true" + nl +
      "$jar.conf={" + nl +
      "  <classifier>test</classifier>" + nl +
      "}" + nl +
      "$jar.depends={" + nl +
      "  <dependency>" + nl +
      "    <groupId>org.apache.ant</groupId>" + nl +
      "    <artifactId>ant</artifactId>" + nl +
      "    <version>1.7.1</version>" + nl +
      "  </dependency>" + nl +
      "}" + nl +
      "$jar.execs={" + nl +
      "  <execution>" + nl +
      "    <id>execution1</id>" + nl +
      "    <phase>jar</phase>" + nl +
      "  </execution>" + nl +
      "}" + nl
    );
    Build.plugin(poml, xml);
    xml(
        "      <plugin>" + nl +
        "        <groupId>org.apache.maven.plugins</groupId>" + nl +
        "        <artifactId>maven-jar-plugin</artifactId>" + nl +
        "        <version>2.6</version>" + nl +
        "        <extensions>false</extensions>" + nl +
        "        <inherited>true</inherited>" + nl +
        "        <configuration>" + nl +
        "          <classifier>test</classifier>" + nl +
        "        </configuration>" + nl +
        "        <dependencies>" + nl +
        "          <dependency>" + nl +
        "            <groupId>org.apache.ant</groupId>" + nl +
        "            <artifactId>ant</artifactId>" + nl +
        "            <version>1.7.1</version>" + nl +
        "          </dependency>" + nl +
        "        </dependencies>" + nl +
        "        <executions>" + nl +
        "          <execution>" + nl +
        "            <id>execution1</id>" + nl +
        "            <phase>jar</phase>" + nl +
        "          </execution>" + nl +
        "        </executions>" + nl +
        "      </plugin>" + nl
    );
  }

  // @Test TODO エラー発生するように対応済み、そのように修正。
  public void ng_conf() {
    poml(
      "plugin=$jar" + nl +
      "$jar=org.apache.maven.plugins:maven-jar-plugin:2.6" + nl +
      "$jar.conf={" + nl +
      "}" + nl
    );
    Build.plugin(poml, xml);
    xml(
        "      <plugin>" + nl +
        "        <groupId>org.apache.maven.plugins</groupId>" + nl +
        "        <artifactId>maven-jar-plugin</artifactId>" + nl +
        "        <version>2.6</version>" + nl +
        "        <extensions>false</extensions>" + nl +
        "        <inherited>true</inherited>" + nl +
        "        <configuration>" + nl +
        "          <classifier>test</classifier>" + nl +
        "        </configuration>" + nl +
        "        <dependencies>" + nl +
        "          <dependency>" + nl +
        "            <groupId>org.apache.ant</groupId>" + nl +
        "            <artifactId>ant</artifactId>" + nl +
        "            <version>1.7.1</version>" + nl +
        "          </dependency>" + nl +
        "        </dependencies>" + nl +
        "        <executions>" + nl +
        "          <execution>" + nl +
        "            <id>execution1</id>" + nl +
        "            <phase>jar</phase>" + nl +
        "          </execution>" + nl +
        "        </executions>" + nl +
        "      </plugin>" + nl
    );
  }
}
