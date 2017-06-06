package poml.convert;

import org.junit.Test;

import poml.UtCase;

public class BasicPkgTest extends UtCase {

  @Test public void id_ver() {
    poml(
      "pkg=group.com:artifact:0.0.1"
    );
    Basic.pkg(poml, xml);
    xml(
      "  <groupId>group.com</groupId>" + nl +
      "  <artifactId>artifact</artifactId>" + nl +
      "  <version>0.0.1</version>" + nl
    );
  }

  @Test public void id_ver_pkg() {
    poml(
      "pkg=group.com:artifact:0.0.1:jar"
    );
    Basic.pkg(poml, xml);
    xml(
      "  <groupId>group.com</groupId>" + nl +
      "  <artifactId>artifact</artifactId>" + nl +
      "  <version>0.0.1</version>" + nl +
      "  <packaging>jar</packaging>" + nl
    );
  }
}
