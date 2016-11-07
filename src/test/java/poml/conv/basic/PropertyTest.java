package poml.conv.basic;

import static org.junit.Assert.fail;

import org.junit.Test;

import poml.conv.ConvTestCase;

public class PropertyTest extends ConvTestCase {

  Property conv = new Property();
  
  @Test public void single() {
    poml.conf.append("property=project.build.sourceEncoding:UTF-8");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "  <properties>" + nl + 
      "    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>" + nl +
      "  </properties>" + nl
    );
  }
  
  @Test public void multi() {
    poml.conf.append("property=");
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
    poml.conf.append("property=");
    poml.conf.load();
    try { 
      conv.convert(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config");
    }
  }

  @Test public void ng_badConf() {
    poml.conf.append("property=:");
    poml.conf.load();
    try { 
      conv.convert(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config");
    }
  }
  
  @Test public void ng_badConf2() {
    poml.conf.append("property=");
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
