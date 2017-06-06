package poml.convert;

import org.junit.Test;

import poml.UtCase;

public class BasicParentTest extends UtCase {

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
}
