package poml.conv.env;

import static org.junit.Assert.fail;

import org.junit.Test;

import poml.conv.ConvTestCase;

public class IssueTest extends ConvTestCase {

  Issue conv = new Issue();

  @Test public void system_url() {
    poml.conf.append("issue=");
    poml.conf.append("  system:GitHub Issues,");
    poml.conf.append("  url:https://github.com/mamorum/poml/issues");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "  <issueManagement>" + nl +
      "    <system>GitHub Issues</system>" + nl +
      "    <url>https://github.com/mamorum/poml/issues</url>" + nl +
      "  </issueManagement>" + nl
    );
  }

  @Test public void ng_badConf() {
    poml.conf.append("issue=system:GitHub Issues");
    poml.conf.load();
    try { 
      conv.convert(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config", true);
    }
  }
}
