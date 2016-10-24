package poml.converter.build.plugin;

import org.junit.Test;

import poml.ConverterCase;

public class JavadocTest extends ConverterCase {

  Javadoc conveter = new Javadoc();

  @Test public void defaultVer() {
    // Nothing to configure. 
    //   poml.conf.append("source=");
    //   poml.conf.load();
    conveter.convert(poml, xml);
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
    conveter.convert(poml, xml);
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
