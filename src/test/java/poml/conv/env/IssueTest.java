package poml.conv.env;

import static org.junit.Assert.fail;

import org.junit.Test;

import poml.conv.ConvTestCase;
import poml.in.Poml;

public class IssueTest extends ConvTestCase {

  Issue conv = new Issue();

  @Test public void url() {
    poml = Poml.parse(data(
      "issue=url>https://github.com/mamorum/poml/issues"
    ));
    conv.convert(poml, xml);
    result(
      "  <issueManagement>" + nl +
      "    <url>https://github.com/mamorum/poml/issues</url>" + nl +
      "  </issueManagement>" + nl
    );
  }

  @Test public void all() {
    poml = Poml.parse(data(
      "issue=" + nl +
      "  system>GitHub Issues," + nl +
      "  url>https://github.com/mamorum/poml/issues" + nl
    ));
    conv.convert(poml, xml);
    result(
      "  <issueManagement>" + nl +
      "    <system>GitHub Issues</system>" + nl +
      "    <url>https://github.com/mamorum/poml/issues</url>" + nl +
      "  </issueManagement>" + nl
    );
  }

  @Test public void ng_badConf() {
    poml = Poml.parse(data(
      "issue=bad"
    ));
    try {
      conv.convert(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config", true);
    }
  }
}
