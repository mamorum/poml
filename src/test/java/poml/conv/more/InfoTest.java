package poml.conv.more;

import static org.junit.Assert.fail;

import org.junit.Test;

import poml.conv.ConvTestCase;
import poml.conv.more.Info;

public class InfoTest extends ConvTestCase {

  Info conv = new Info();
  
  @Test public void name() {
    poml.conf.append("info=name:INFO");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "  <name>INFO</name>" + nl
    );
  }
  
  @Test public void all() {
    poml.conf.append("info=");
    poml.conf.append("  name: INFO,");
    poml.conf.append("  description: More Project Infomation,");
    poml.conf.append("  url: https://github.com/mamorum/poml,");
    poml.conf.append("  inceptionYear: 2016");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
        "  <name>INFO</name>" + nl + 
        "  <description>More Project Infomation</description>" + nl +
        "  <url>https://github.com/mamorum/poml</url>" + nl +
        "  <inceptionYear>2016</inceptionYear>" + nl
    );
  }

  @Test public void ng_badConf() {
    poml.conf.append("info=bad-conf");
    poml.conf.load();
    try { 
      conv.convert(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config");
    }
  }
}
