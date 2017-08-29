package poml.convert;

import org.junit.Test;

import poml.UtCase;

public class BuildTest extends UtCase {
  //-> Build#all
  @Test public void all_builds() {
    poml(
      "build.base=finalName>demo" + nl
    );
    Build.all(poml, xml);
    xml(
      nl +
      "  <build>" + nl +
      "    <finalName>demo</finalName>" + nl +
      "  </build>" + nl
    );
  }
  @Test public void all_plugin() {
    poml(
      "plugin=$war" + nl +
      "$war=:maven-war-plugin:3.1.0" + nl
    );
    Build.all(poml, xml);
    xml(
      nl +
      "  <build>" + nl +
      "    <plugins>" + nl +
      "      <plugin>" + nl +
      "        <artifactId>maven-war-plugin</artifactId>" + nl +
      "        <version>3.1.0</version>" + nl +
      "      </plugin>" + nl +
      "    </plugins>" + nl +
      "  </build>" + nl
    );
  }

  @Test public void all_builds_plugin() {
    poml(
      "build.base=finalName>demo" + nl +
      "plugin=$war" + nl +
      "$war=:maven-war-plugin:3.1.0" + nl
    );
    Build.all(poml, xml);
    xml(
      nl +
      "  <build>" + nl +
      "    <finalName>demo</finalName>" + nl +
      "    <plugins>" + nl +
      "      <plugin>" + nl +
      "        <artifactId>maven-war-plugin</artifactId>" + nl +
      "        <version>3.1.0</version>" + nl +
      "      </plugin>" + nl +
      "    </plugins>" + nl +
      "  </build>" + nl
    );
  }

  //-> builds=k>v, k>v, ...
  @Test public void builds_multi() {
    poml(
      "build.base=" + nl +
      " defaultGoal>test, finalName>demo" + nl
    );
    Build.base(poml, xml);
    xml(
      "    <defaultGoal>test</defaultGoal>" + nl +
      "    <finalName>demo</finalName>" + nl
    );
  }
  @Test public void builds_single() {
    poml(
      "build.base=finalName>demo" + nl
    );
    Build.base(poml, xml);
    xml(
      "    <finalName>demo</finalName>" + nl
    );
  }

  //-> plugin=$1, $2, ...
  @Test public void plugin_multi() {
    poml(
      "plugin=$demo, $compiler" + nl +
      "$demo=org.demo:demo-plugin" + nl +
      "$compiler=:maven-compiler-plugin:3.6.1" + nl +
      "$compiler.conf={" + nl +
      "  <fork>true</fork>" + nl +
      "}" + nl
    );
    Build.plugin(poml, xml);
    xml(
      "      <plugin>" + nl +
      "        <groupId>org.demo</groupId>" + nl +
      "        <artifactId>demo-plugin</artifactId>" + nl +
      "      </plugin>" + nl +
      "      <plugin>" + nl +
      "        <artifactId>maven-compiler-plugin</artifactId>" + nl +
      "        <version>3.6.1</version>" + nl +
      "        <configuration>" + nl +
      "          <fork>true</fork>" + nl +
      "        </configuration>" + nl +
      "      </plugin>" + nl
    );
  }

  @Test public void plugin_single_all_conf() {
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

  // -> &fatjar
  @Test public void plugin_fatjar_default() {
    poml(
      "plugin=&fatjar" + nl
    );
    Build.plugin(poml, xml);
    xml(
      "      <plugin>" + nl +
      "        <groupId>org.apache.maven.plugins</groupId>" + nl +
      "        <artifactId>maven-assembly-plugin</artifactId>" + nl +
      "        <version>2.6</version>" + nl +
      "        <configuration>" + nl +
      "          <finalName>${project.artifactId}</finalName>" + nl +
      "          <descriptorRefs>" + nl +
      "            <descriptorRef>jar-with-dependencies</descriptorRef>" + nl +
      "          </descriptorRefs>" + nl +
      "          <appendAssemblyId>false</appendAssemblyId>" + nl +
      "          <attach>false</attach>" + nl +
      "        </configuration>" + nl +
      "        <executions>" + nl +
      "          <execution>" + nl +
      "            <id>make-assembly</id>" + nl +
      "            <phase>package</phase>" + nl +
      "            <goals><goal>single</goal></goals>" + nl +
      "          </execution>" + nl +
      "        </executions>" + nl +
      "      </plugin>" + nl
    );
  }
  @Test public void plugin_fatjar_all_conf() {
    poml(
      "plugin=&fatjar" + nl +
      "&fatjar=" + nl +
      "  version>3.0.0, finalName>poml, mainClass>poml.Main"+ nl +
      "&fatjar.conf+={"+ nl +
      "  <outputDirectory>dist</outputDirectory>"+ nl +
      "}"+ nl +
      "&fatjar.conf.archive+={"+ nl +
      "  <manifestEntries>"+ nl +
      "    <Implementation-Version>${project.version}</Implementation-Version>"+ nl +
      "    <Built-By>Poml Authors</Built-By>"+ nl +
      "  </manifestEntries>"+ nl +
      "}"+ nl
    );
    Build.plugin(poml, xml);
    xml(
      "      <plugin>" + nl +
      "        <groupId>org.apache.maven.plugins</groupId>" + nl +
      "        <artifactId>maven-assembly-plugin</artifactId>" + nl +
      "        <version>3.0.0</version>" + nl +
      "        <configuration>" + nl +
      "          <finalName>poml</finalName>" + nl +
      "          <outputDirectory>dist</outputDirectory>" + nl +
      "          <archive>" + nl +
      "            <manifest>" + nl +
      "              <mainClass>poml.Main</mainClass>" + nl +
      "            </manifest>" + nl +
      "            <manifestEntries>" + nl +
      "              <Implementation-Version>${project.version}</Implementation-Version>"+ nl +
      "              <Built-By>Poml Authors</Built-By>" + nl +
      "            </manifestEntries>" + nl +
      "          </archive>" + nl +
      "          <descriptorRefs>" + nl +
      "            <descriptorRef>jar-with-dependencies</descriptorRef>" + nl +
      "          </descriptorRefs>" + nl +
      "          <appendAssemblyId>false</appendAssemblyId>" + nl +
      "          <attach>false</attach>" + nl +
      "        </configuration>" + nl +
      "        <executions>" + nl +
      "          <execution>" + nl +
      "            <id>make-assembly</id>" + nl +
      "            <phase>package</phase>" + nl +
      "            <goals><goal>single</goal></goals>" + nl +
      "          </execution>" + nl +
      "        </executions>" + nl +
      "      </plugin>" + nl
    );
  }

  // -> &ossrh
  @Test public void plugin_ossrh() {
    poml(
      "plugin=&ossrh" + nl
    );
    Build.plugin(poml, xml);
    xml(
      "      <plugin>" + nl +
      "        <groupId>org.apache.maven.plugins</groupId>" + nl +
      "        <artifactId>maven-source-plugin</artifactId>" + nl +
      "        <version>2.2.1</version>" + nl +
      "        <executions>" + nl +
      "          <execution>" + nl +
      "            <id>attach-sources</id>" + nl +
      "            <goals><goal>jar-no-fork</goal></goals>" + nl +
      "          </execution>" + nl +
      "        </executions>" + nl +
      "      </plugin>" + nl +
      "      <plugin>" + nl +
      "        <groupId>org.apache.maven.plugins</groupId>" + nl +
      "        <artifactId>maven-javadoc-plugin</artifactId>" + nl +
      "        <version>2.9.1</version>" + nl +
      "        <executions>" + nl +
      "          <execution>" + nl +
      "            <id>attach-javadocs</id>" + nl +
      "            <goals><goal>jar</goal></goals>" + nl +
      "          </execution>" + nl +
      "        </executions>" + nl +
      "      </plugin>" + nl +
      "      <plugin>" + nl +
      "        <groupId>org.apache.maven.plugins</groupId>" + nl +
      "        <artifactId>maven-gpg-plugin</artifactId>" + nl +
      "        <version>1.5</version>" + nl +
      "        <executions>" + nl +
      "          <execution>" + nl +
      "            <id>sign-artifacts</id>" + nl +
      "            <phase>verify</phase>" + nl +
      "            <goals><goal>sign</goal></goals>" + nl +
      "          </execution>" + nl +
      "        </executions>" + nl +
      "      </plugin>" + nl
    );
  }
}
