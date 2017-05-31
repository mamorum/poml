package poml.convert;

import static org.junit.Assert.fail;

import org.junit.Test;

public class EnvIssueTest extends TestCase {

  @Test public void url() {
    poml(
      "issue=url>https://github.com/mamorum/poml/issues"
    );
    Env.issue(poml, xml);
    xml(
      "  <issueManagement>" + nl +
      "    <url>https://github.com/mamorum/poml/issues</url>" + nl +
      "  </issueManagement>" + nl
    );
  }

  @Test public void all() {
    poml(
      "issue=" + nl +
      "  system>GitHub Issues," + nl +
      "  url>https://github.com/mamorum/poml/issues" + nl
    );
    Env.issue(poml, xml);
    xml(
      "  <issueManagement>" + nl +
      "    <system>GitHub Issues</system>" + nl +
      "    <url>https://github.com/mamorum/poml/issues</url>" + nl +
      "  </issueManagement>" + nl
    );
  }

  @Test public void ng_badConf() {
    poml(
      "issue=bad"
    );
    try {
      Env.issue(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config", true);
    }
  }
}