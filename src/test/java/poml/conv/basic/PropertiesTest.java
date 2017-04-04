package poml.conv.basic;

import static org.junit.Assert.fail;

import org.junit.Test;

import poml.conv.ConvTestCase;

public class PropertiesTest extends ConvTestCase {

  Properties conv = new Properties();
  
  @Test public void single() {
    poml.conf.append("properties=project.build.sourceEncoding:UTF-8");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "  <properties>" + nl + 
      "    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>" + nl +
      "  </properties>" + nl
    );
  }
  
  @Test public void multi() {
    poml.conf.append("properties=");
    poml.conf.append("  property:value,");
    poml.conf.append("  project.build.sourceEncoding:UTF-8");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "  <properties>" + nl + 
      "    <property>value</property>" + nl +
      "    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>" + nl +
      "  </properties>" + nl
    );
  }
  
  @Test public void replaceEncoding() {
    poml.conf.append("properties=&encoding:UTF-8");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "  <properties>" + nl + 
      "    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>" + nl +
      "    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>" + nl +
      "  </properties>" + nl
    );
  }
  @Test public void replaceCompiler() {
    poml.conf.append("properties=&compiler:1.8");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "  <properties>" + nl + 
      "    <maven.compiler.source>1.8</maven.compiler.source>" + nl +
      "    <maven.compiler.target>1.8</maven.compiler.target>" + nl +
      "  </properties>" + nl
    );
  }
  
  @Test public void replaceMulti() {
    poml.conf.append("properties=");
    poml.conf.append("  property1:value1,");
    poml.conf.append("  &encoding:UTF-8,");
    poml.conf.append("  property2:value2,");
    poml.conf.append("  &compiler:1.8,");
    poml.conf.append("  property3:value3");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "  <properties>" + nl + 
      "    <property1>value1</property1>" + nl +
      "    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>" + nl +
      "    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>" + nl +
      "    <property2>value2</property2>" + nl +
      "    <maven.compiler.source>1.8</maven.compiler.source>" + nl +
      "    <maven.compiler.target>1.8</maven.compiler.target>" + nl +
      "    <property3>value3</property3>" + nl +
      "  </properties>" + nl
    );
  }
  
  @Test public void ng_noConf() {
    poml.conf.load();
    try { 
      conv.convert(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Config not found");
    }
  }

  @Test public void ng_emptyConf() {
    poml.conf.append("properties=");
    poml.conf.load();
    try { 
      conv.convert(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config");
    }
  }

  @Test public void ng_badConf() {
    poml.conf.append("properties=:");
    poml.conf.load();
    try { 
      conv.convert(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config");
    }
  }
  
  @Test public void ng_badConf2() {
    poml.conf.append("properties=");
    poml.conf.append("  key:val,");
    poml.conf.append("  keyval");
    poml.conf.load();
    try { 
      conv.convert(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config");
    }
  }
}
