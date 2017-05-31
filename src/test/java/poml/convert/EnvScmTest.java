package poml.convert;

import static org.junit.Assert.fail;

import org.junit.Test;

public class EnvScmTest extends TestCase {

  @Test public void url() {
    poml(
      "scm=" + nl +
      "  url>https://github.com/mamorum/poml/" + nl
    );
    Env.scm(poml, xml);
    xml(
      "  <scm>" + nl +
      "    <url>https://github.com/mamorum/poml/</url>" + nl +
      "  </scm>" + nl
    );
  }

  @Test public void all() {
    poml(
      "scm=" + nl +
      "  connection>scm:git:https://github.com/mamorum/poml.git," + nl +
      "  developerConnection>scm:git:git@github.com:mamorum/poml.git," + nl +
      "  tag>HEAD," + nl +
      "  url>https://github.com/mamorum/poml" + nl
    );
    Env.scm(poml, xml);
    xml(
      "  <scm>" + nl +
      "    <connection>scm:git:https://github.com/mamorum/poml.git</connection>" + nl +
      "    <developerConnection>scm:git:git@github.com:mamorum/poml.git</developerConnection>" + nl +
      "    <tag>HEAD</tag>" + nl +
      "    <url>https://github.com/mamorum/poml</url>" + nl +
      "  </scm>" + nl
    );
  }

  @Test public void ng_badConf() {
    poml(
      "scm=badconf"
    );
    try {
      Env.scm(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config", false);
    }
  }
}
