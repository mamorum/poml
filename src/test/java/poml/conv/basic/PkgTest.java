package poml.conv.basic;

import static org.junit.Assert.fail;

import org.junit.Test;

import poml.Poml;
import poml.conv.ConvTestCase;
import poml.convert.Basic;

public class PkgTest extends ConvTestCase {

  @Test public void id_ver() {
    poml = Poml.parse(data(
      "pkg=group.com:artifact:0.0.1"
    ));
    Basic.pkg(poml, xml);
    result(
      "  <groupId>group.com</groupId>" + nl +
      "  <artifactId>artifact</artifactId>" + nl +
      "  <version>0.0.1</version>" + nl
    );
  }

  @Test public void id_ver_pkg() {
    poml = Poml.parse(data(
      "pkg=group.com:artifact:0.0.1:jar"
    ));
    Basic.pkg(poml, xml);
    result(
      "  <groupId>group.com</groupId>" + nl +
      "  <artifactId>artifact</artifactId>" + nl +
      "  <version>0.0.1</version>" + nl +
      "  <packaging>jar</packaging>" + nl
    );
  }

  @Test public void ng_noConf() {
    poml = Poml.parse(data(""));
    try {
      Basic.pkg(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Config not found");
    }
  }

  @Test public void ng_emptyConf() {
    poml = Poml.parse(data("pkg="));
    try {
      Basic.pkg(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Config not found");
    }
  }

  @Test public void ng_badConf() {
    poml = Poml.parse(data("pkg=group.com:::"));
    try {
      Basic.pkg(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config");
    }
  }
}
