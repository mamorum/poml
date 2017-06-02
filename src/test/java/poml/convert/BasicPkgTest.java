package poml.convert;

import static org.junit.Assert.fail;

import org.junit.Test;

public class BasicPkgTest extends TestCase {

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

  @Test public void ng_noConf() {
    poml("");
    try {
      Basic.pkg(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Config not found");
    }
  }

  @Test public void ng_emptyConf() {
    poml("pkg=");
    try {
      Basic.pkg(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Config not found");
    }
  }

  @Test public void ng_val() {
    poml("pkg=group.com:::");
    try {
      Basic.pkg(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).is(
        "Invalid config val [key=pkg] [val=group.com:::]"
      );
    }
  }
}
