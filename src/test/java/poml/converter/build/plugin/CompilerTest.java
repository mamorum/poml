package poml.converter.build.plugin;

import org.junit.Test;

import poml.ConverterCase;

public class CompilerTest extends ConverterCase {

  Compiler conveter = new Compiler();
  
  @Test public void ver() {
    src.prop.put("compiler", "version:1.0.0");
    conveter.convert(src, dst);
    output.is(
      "      <plugin>" + nl + 
      "        <groupId>org.apache.maven.plugins</groupId>" + nl +
      "        <artifactId>maven-compiler-plugin</artifactId>" + nl +
      "        <version>1.0.0</version>" + nl +
      "      </plugin>" + nl
    );
  }
  
  @Test public void ver_conf() {
    src.prop.put("compiler", "version:1.0.0");
    src.prop.put("compiler.conf", "source:1.8, target:1.8");
    conveter.convert(src, dst);
    output.is(
        "      <plugin>" + nl + 
        "        <groupId>org.apache.maven.plugins</groupId>" + nl +
        "        <artifactId>maven-compiler-plugin</artifactId>" + nl +
        "        <version>1.0.0</version>" + nl +
        "        <configuration>" + nl +
        "          <source>1.8</source>" + nl +
        "          <target>1.8</target>" + nl +
        "        </configuration>" + nl +
        "      </plugin>" + nl
      );
  }

  @Test public void conf() {
    src.prop.put("compiler.conf", "source:1.8, target:1.8");
    conveter.convert(src, dst);
    output.is(
        "      <plugin>" + nl + 
        "        <groupId>org.apache.maven.plugins</groupId>" + nl +
        "        <artifactId>maven-compiler-plugin</artifactId>" + nl +
        "        <version>3.5.1</version>" + nl +
        "        <configuration>" + nl +
        "          <source>1.8</source>" + nl +
        "          <target>1.8</target>" + nl +
        "        </configuration>" + nl +
        "      </plugin>" + nl
      );
  }
}
