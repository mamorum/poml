package poml.converter.build.plugin;

import org.junit.Test;

import poml.ConverterCase;

public class SourceTest extends ConverterCase {

  Source conv = new Source();

  @Test public void none() {
    poml.conf.append("source=_none");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "      <plugin>" + nl +
      "        <groupId>org.apache.maven.plugins</groupId>" + nl +
      "        <artifactId>maven-source-plugin</artifactId>" + nl +
      "        <version>3.0.1</version>" + nl +
      "        <executions>" + nl +
      "          <execution>" + nl +
      "            <id>attach-sources</id>" + nl +
      "            <goals><goal>jar-no-fork</goal></goals>" + nl +
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
      "        <artifactId>maven-source-plugin</artifactId>" + nl +
      "        <version>3.0.1</version>" + nl +
      "        <executions>" + nl +
      "          <execution>" + nl +
      "            <id>attach-sources</id>" + nl +
      "            <goals><goal>jar-no-fork</goal></goals>" + nl +
      "          </execution>" + nl +
      "        </executions>" + nl +
      "      </plugin>" + nl
    );
  }

  @Test public void ver() {
    poml.conf.append("source=ver:1.0.0");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "      <plugin>" + nl +
      "        <groupId>org.apache.maven.plugins</groupId>" + nl +
      "        <artifactId>maven-source-plugin</artifactId>" + nl +
      "        <version>1.0.0</version>" + nl +
      "        <executions>" + nl +
      "          <execution>" + nl +
      "            <id>attach-sources</id>" + nl +
      "            <goals><goal>jar-no-fork</goal></goals>" + nl +
      "          </execution>" + nl +
      "        </executions>" + nl +
      "      </plugin>" + nl
    );
  }
}
