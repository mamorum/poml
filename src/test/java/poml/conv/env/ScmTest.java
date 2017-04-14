package poml.conv.env;

import static org.junit.Assert.fail;

import org.junit.Test;

import poml.conv.ConvTestCase;

public class ScmTest extends ConvTestCase {

  Scm conv = new Scm();

  @Test public void url() {
    poml.conf.parse(in(
      "scm=" + nl +
      "  url:https://github.com/mamorum/poml/" + nl
    ));
    conv.convert(poml, xml);
    output.is(
      "  <scm>" + nl +
      "    <url>https://github.com/mamorum/poml/</url>" + nl +
      "  </scm>" + nl
    );
  }
  
  @Test public void all() {
    poml.conf.parse(in(
      "scm=" + nl +
      "  connect: scm:git:https://github.com/mamorum/poml.git," + nl +
      "  devConnect: scm:git:git@github.com:mamorum/poml.git," + nl +
      "  tag: HEAD," + nl +
      "  url: https://github.com/mamorum/poml" + nl
    ));
    conv.convert(poml, xml);
    output.is(
      "  <scm>" + nl +
      "    <url>https://github.com/mamorum/poml</url>" + nl +
      "    <connection>scm:git:https://github.com/mamorum/poml.git</connection>" + nl +
      "    <developerConnection>scm:git:git@github.com:mamorum/poml.git</developerConnection>" + nl +
      "    <tag>HEAD</tag>" + nl +
      "  </scm>" + nl
    );
  }

  @Test public void ng_badConf() {
    poml.conf.parse(in(
      "scm=badconf"
    ));
    try { 
      conv.convert(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config");
    }
  }
}
