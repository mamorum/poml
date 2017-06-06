package poml.convert;

import static org.junit.Assert.fail;

import org.junit.Test;

import poml.UtCase;

public class MoreInfoTest extends UtCase {

  @Test public void name() {
    poml(
      "info=name>INFO"
    );
    More.info(poml, xml);
    xml(
      "  <name>INFO</name>" + nl
    );
  }

  @Test public void all() {
    poml(
      "info=" + nl +
      "  name>INFO," + nl +
      "  description>More Project Infomation," + nl +
      "  url>https://github.com/mamorum/poml," + nl +
      "  inceptionYear>2016" + nl
    );
    More.info(poml, xml);
    xml(
        "  <name>INFO</name>" + nl +
        "  <description>More Project Infomation</description>" + nl +
        "  <url>https://github.com/mamorum/poml</url>" + nl +
        "  <inceptionYear>2016</inceptionYear>" + nl
    );
  }

  @Test public void ng_badConf() {
    poml(
      "info=bad-conf"
    );
    try {
      More.info(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      err("Invalid config [key=info] [val=bad-conf]", e);
    }
  }
}
