package poml.converter.build.plugin;

import org.junit.Test;

import poml.ConverterCase;

public class ExeTest extends ConverterCase {

  Exec conveter = new Exec();

  @Test public void defaultVer() {
    src.prop.put("exe", "mainClass:org.Main");
    conveter.convert(src, dst);
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
    src.prop.put("exe", "ver:1.0.0, mainClass:org.Main");
    conveter.convert(src, dst);
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
