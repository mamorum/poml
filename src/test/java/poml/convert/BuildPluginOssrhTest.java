package poml.convert;

import org.junit.Test;

import poml.UtCase;

public class BuildPluginOssrhTest extends UtCase {
  @Test public void test() {
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
