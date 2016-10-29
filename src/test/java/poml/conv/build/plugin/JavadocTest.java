package poml.conv.build.plugin;

import org.junit.Test;

import poml.conv.ConvTestCase;
import poml.conv.build.plugin.Javadoc;

public class JavadocTest extends ConvTestCase {

  Javadoc conv = new Javadoc();

  @Test public void none() {
    // Nothing to configure. 
    poml.conf.append("source=_none");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "      <plugin>" + nl +
      "        <groupId>org.apache.maven.plugins</groupId>" + nl +
      "        <artifactId>maven-javadoc-plugin</artifactId>" + nl +
      "        <version>2.10.4</version>" + nl +
      "        <executions>" + nl +
      "          <execution>" + nl +
      "            <id>attach-javadocs</id>" + nl +
      "            <goals><goal>jar</goal></goals>" + nl +
      "          </execution>" + nl +
      "        </executions>" + nl +
      "      </plugin>" + nl
    );
  }
  
  @Test public void defaultVer() {
    // Nothing to configure.
    conv.convert(poml, xml);
    output.is(
      "      <plugin>" + nl +
      "        <groupId>org.apache.maven.plugins</groupId>" + nl +
      "        <artifactId>maven-javadoc-plugin</artifactId>" + nl +
      "        <version>2.10.4</version>" + nl +
      "        <executions>" + nl +
      "          <execution>" + nl +
      "            <id>attach-javadocs</id>" + nl +
      "            <goals><goal>jar</goal></goals>" + nl +
      "          </execution>" + nl +
      "        </executions>" + nl +
      "      </plugin>" + nl
    );
  }

  @Test public void ver() {
    poml.conf.append("javadoc=ver:1.0.0");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "      <plugin>" + nl +
      "        <groupId>org.apache.maven.plugins</groupId>" + nl +
      "        <artifactId>maven-javadoc-plugin</artifactId>" + nl +
      "        <version>1.0.0</version>" + nl +
      "        <executions>" + nl +
      "          <execution>" + nl +
      "            <id>attach-javadocs</id>" + nl +
      "            <goals><goal>jar</goal></goals>" + nl +
      "          </execution>" + nl +
      "        </executions>" + nl +
      "      </plugin>" + nl
    );
  }
}
