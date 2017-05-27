package poml.conv.basic;

import static org.junit.Assert.fail;

import org.junit.Test;

import poml.Poml;
import poml.conv.ConvTestCase;
import poml.convert.Basic;

public class ParentTest extends ConvTestCase {

  @Test public void id_ver() {
    poml = Poml.parse(data(
      "parent=parent.com:parent:1.0.0"
    ));
    Basic.parent(poml, xml);
    result(
      "  <parent>" + nl +
      "    <groupId>parent.com</groupId>" + nl +
      "    <artifactId>parent</artifactId>" + nl +
      "    <version>1.0.0</version>" + nl +
      "  </parent>" + nl
    );
  }

  @Test public void ng_noConf() {
    poml = Poml.parse(data(""));
    try {
      Basic.parent(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Config not found");
    }
  }

  @Test public void ng_emptyConf() {
    poml = Poml.parse(data("parent="));
    try {
      Basic.parent(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Config not found");
    }
  }

  @Test public void ng_badConf() {
    poml = Poml.parse(data("parent=parent.com::"));
    try {
      Basic.parent(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config");
    }
  }
}
