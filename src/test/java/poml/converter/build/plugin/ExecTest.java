package poml.converter.build.plugin;

import org.junit.Test;

import poml.ConverterCase;

public class ExecTest extends ConverterCase {

  Exec conv = new Exec();

  @Test public void defaultVer() {
    poml.conf.append("exec=mainClass:org.Main");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "      <plugin>" + nl +
      "        <groupId>org.codehaus.mojo</groupId>" + nl +
      "        <artifactId>exec-maven-plugin</artifactId>" + nl +
      "        <version>1.5.0</version>" + nl +
      "        <configuration>" + nl +
      "          <mainClass>org.Main</mainClass>" + nl +
      "        </configuration>" + nl +
      "      </plugin>" + nl
    );
  }

  @Test public void ver() {
    poml.conf.append("exec=ver:1.0.0, mainClass:org.Main");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "      <plugin>" + nl +  
      "        <groupId>org.codehaus.mojo</groupId>" + nl +
      "        <artifactId>exec-maven-plugin</artifactId>" + nl +
      "        <version>1.0.0</version>" + nl +
      "        <configuration>" + nl +
      "          <mainClass>org.Main</mainClass>" + nl +
      "        </configuration>" + nl +
      "      </plugin>" + nl
    );
  }
}
