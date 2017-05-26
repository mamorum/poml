package poml.conv.env;

import static org.junit.Assert.fail;

import org.junit.Test;

import poml.Poml;
import poml.conv.ConvTestCase;
import poml.convert.Env;

public class IssueTest extends ConvTestCase {

  @Test public void url() {
    poml = Poml.parse(data(
      "issue=url>https://github.com/mamorum/poml/issues"
    ));
    Env.issue(poml, xml);
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
    Env.issue(poml, xml);
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
      Env.issue(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config", true);
    }
  }
}
