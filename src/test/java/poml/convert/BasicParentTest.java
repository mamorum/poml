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

  @Test public void ng_none() {
    poml("");
    try {
      Basic.parent(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      err("Invalid config [key=parent] [val=null]", e);
    }
  }

  @Test public void ng_empty() {
    poml("parent=");
    try {
      Basic.parent(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      err("Invalid config [key=parent] [val=]", e);
    }
  }

  @Test public void ng_val() {
    poml("parent=parent.com::");
    try {
      Basic.parent(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      err("Invalid config [key=parent] [val=parent.com::]", e);
    }
  }
}
