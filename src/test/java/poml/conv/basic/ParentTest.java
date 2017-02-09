package poml.conv.basic;

import static org.junit.Assert.fail;
import org.junit.Test;

import poml.conv.ConvTestCase;

public class ParentTest extends ConvTestCase {

  Parent conv = new Parent();
  
  @Test public void id_ver() {
    poml.conf.append("parent=parent.com:parent:1.0.0");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "  <parent>" + nl +
      "    <groupId>parent.com</groupId>" + nl + 
      "    <artifactId>parent</artifactId>" + nl +
      "    <version>1.0.0</version>" + nl +
      "  </parent>" + nl
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
    poml.conf.append("parent=");
    poml.conf.load();
    try { 
      conv.convert(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config");
    }
  }

  @Test public void ng_badConf() {
    poml.conf.append("parent=parent.com::");
    poml.conf.load();
    try { 
      conv.convert(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config");
    }
  }
}
