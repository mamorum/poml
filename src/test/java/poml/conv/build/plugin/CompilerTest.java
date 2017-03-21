package poml.conv.build.plugin;

import static org.junit.Assert.fail;

import org.junit.Test;

import poml.conv.ConvTestCase;
import poml.conv.build.plugin.Compiler;

public class CompilerTest extends ConvTestCase {

  Compiler conv = new Compiler();

  @Test public void defaultVer() {
    poml.conf.append("compiler=source:1.8, target:1.8");
    poml.conf.load();
    conv.convert(poml, xml);
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
  
  @Test public void ver() {
    poml.conf.append("compiler=ver:1.0.0, source:1.8, target:1.8");
    poml.conf.load();
    conv.convert(poml, xml);
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

  @Test public void ng_badConf() {
    poml.conf.append("compiler=ver:1.0.0, source:1.8");
    poml.conf.load();
    try { 
      conv.convert(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config", true);
    }
  }
}