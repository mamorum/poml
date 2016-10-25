package poml.converter.build.plugin;

import org.junit.Test;

import poml.ConverterCase;

public class GpgTest extends ConverterCase {

  Gpg conveter = new Gpg();

  @Test public void defaultVer() {
    // Nothing to configure. 
    //   poml.conf.append("gpg=");
    //   poml.conf.load();
    conveter.convert(poml, xml);
    output.is(
        "      <plugin>" + nl +
        "        <groupId>org.apache.maven.plugins</groupId>" + nl +
        "        <artifactId>maven-gpg-plugin</artifactId>" + nl +
        "        <version>1.6</version>" + nl +
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

  @Test public void ver() {
    poml.conf.append("gpg=ver:1.0.0");
    poml.conf.load();
    conveter.convert(poml, xml);
    output.is(
        "      <plugin>" + nl +
        "        <groupId>org.apache.maven.plugins</groupId>" + nl +
        "        <artifactId>maven-gpg-plugin</artifactId>" + nl +
        "        <version>1.0.0</version>" + nl +
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
