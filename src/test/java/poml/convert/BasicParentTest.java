package poml.convert;

import static org.junit.Assert.fail;

import org.junit.Test;

public class BasicParentTest extends TestCase {

  @Test public void id_ver() {
    poml(
      "parent=parent.com:parent:1.0.0"
    );
    Basic.parent(poml, xml);
    xml(
      "  <parent>" + nl +
      "    <groupId>parent.com</groupId>" + nl +
      "    <artifactId>parent</artifactId>" + nl +
      "    <version>1.0.0</version>" + nl +
      "  </parent>" + nl
    );
  }

  @Test public void ng_noConf() {
    poml("");
    try {
      Basic.parent(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).is(
        "Invalid config [key=parent] [val=null]"
      );
    }
  }

  @Test public void ng_emptyConf() {
    poml("parent=");
    try {
      Basic.parent(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).is(
        "Invalid config [key=parent] [val=]"
      );
    }
  }

  @Test public void ng_val() {
    poml("parent=parent.com::");
    try {
      Basic.parent(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).is(
        "Invalid config [key=parent] [val=parent.com::]"
      );
    }
  }
}
