package poml.conv.env;

import static org.junit.Assert.fail;

import org.junit.Test;

import poml.Poml;
import poml.conv.ConvTestCase;
import poml.convert.Env;

public class ScmTest extends ConvTestCase {

  @Test public void url() {
    poml = Poml.parse(data(
      "scm=" + nl +
      "  url>https://github.com/mamorum/poml/" + nl
    ));
    Env.scm(poml, xml);
    result(
      "  <scm>" + nl +
      "    <url>https://github.com/mamorum/poml/</url>" + nl +
      "  </scm>" + nl
    );
  }

  @Test public void all() {
    poml = Poml.parse(data(
      "scm=" + nl +
      "  connection>scm:git:https://github.com/mamorum/poml.git," + nl +
      "  developerConnection>scm:git:git@github.com:mamorum/poml.git," + nl +
      "  tag>HEAD," + nl +
      "  url>https://github.com/mamorum/poml" + nl
    ));
    Env.scm(poml, xml);
    result(
      "  <scm>" + nl +
      "    <connection>scm:git:https://github.com/mamorum/poml.git</connection>" + nl +
      "    <developerConnection>scm:git:git@github.com:mamorum/poml.git</developerConnection>" + nl +
      "    <tag>HEAD</tag>" + nl +
      "    <url>https://github.com/mamorum/poml</url>" + nl +
      "  </scm>" + nl
    );
  }

  @Test public void ng_badConf() {
    poml = Poml.parse(data(
      "scm=badconf"
    ));
    try {
      Env.scm(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config", false);
    }
  }
}
