package poml.converter.build.plugin;

import org.junit.Test;

import poml.ConverterCase;

public class ExecTest extends ConverterCase {

  Exec conveter = new Exec();
  
  @Test public void ver() {
    src.prop.put("exec", "version:1.0.0");
    conveter.convert(src, dst);
    output.is(
      "      <plugin>" + nl + 
      "        <groupId>org.codehaus.mojo</groupId>" + nl +
      "        <artifactId>exec-maven-plugin</artifactId>" + nl +
      "        <version>1.0.0</version>" + nl +
      "      </plugin>" + nl
    );
  }
  
  @Test public void ver_conf() {
    src.prop.put("exec", "version:1.0.0");
    src.prop.put("exec.conf", "mainClass:org.Main");
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

  @Test public void conf() {
    src.prop.put("exec.conf",
        "  mainClass:org.Main, " + nl +
        "  commandlineArgs:-j 20"
    );
    conveter.convert(src, dst);
    output.is(
        "      <plugin>" + nl +
        "        <groupId>org.codehaus.mojo</groupId>" + nl +
        "        <artifactId>exec-maven-plugin</artifactId>" + nl +
        "        <version>1.5.0</version>" + nl +
        "        <configuration>" + nl +
        "          <mainClass>org.Main</mainClass>" + nl +
        "          <commandlineArgs>-j 20</commandlineArgs>" + nl +
        "        </configuration>" + nl +
        "      </plugin>" + nl
      );
  }
}
