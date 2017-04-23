package poml.conv.build;

import org.junit.Test;

import poml.conv.ConvTestCase;

public class PluginTest extends ConvTestCase {

  Plugin conv = new Plugin();

  // $
  @Test public void grp_art() {
    poml.conf.parse(data(
      "plugin=$sbp" + nl +
      "$sbp=org.springframework.boot:spring-boot-maven-plugin"
    ));
    conv.convert(poml, xml);
    result(
      "      <plugin>" + nl +
      "        <groupId>org.springframework.boot</groupId>" + nl +
      "        <artifactId>spring-boot-maven-plugin</artifactId>" + nl +
      "      </plugin>" + nl
    );
  }

  @Test public void grp_exec() {
    poml.conf.parse(data(
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
    ));
    conv.convert(poml, xml);
    result(
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

  // &
  @Test public void fatjar_ossrh() {
    poml.conf.parse(data(
      "plugin=&fatjar, &ossrh" + nl +
      "&fatjar=mainClass>poml.Main"
    ));
    conv.convert(poml, xml);
    result(
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
      "          <archive>" + nl +
      "            <manifest>" + nl +
      "              <mainClass>poml.Main</mainClass>" + nl +
      "            </manifest>" + nl +
      "          </archive>" + nl +
      "        </configuration>" + nl +
      "        <executions>" + nl +
      "          <execution>" + nl +
      "            <id>make-assembly</id>" + nl +
      "            <phase>package</phase>" + nl +
      "            <goals><goal>single</goal></goals>" + nl +
      "          </execution>" + nl +
      "        </executions>" + nl +
      "      </plugin>" + nl +
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


  // $, &
  @Test public void combi() {
    poml.conf.parse(data(
      "plugin=$ant, &ossrh" + nl +
      "$ant=:maven-antrun-plugin:1.1" + nl +
      "$ant.execs={" + nl +
      "  <execution>" + nl +
      "    <id>echodir</id>" + nl +
      "    <!-- ... -->" + nl +
      "  </execution>" + nl +
      "}" + nl
    ));
    conv.convert(poml, xml);
    result(
      "      <plugin>" + nl +
      "        <artifactId>maven-antrun-plugin</artifactId>" + nl +
      "        <version>1.1</version>" + nl +
      "        <executions>" + nl +
      "          <execution>" + nl +
      "            <id>echodir</id>" + nl +
      "            <!-- ... -->" + nl +
      "          </execution>" + nl +
      "        </executions>" + nl +
      "      </plugin>" + nl +
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
