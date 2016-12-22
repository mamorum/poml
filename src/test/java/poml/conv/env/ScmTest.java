package poml.conv.env;

import static org.junit.Assert.fail;

import org.junit.Test;

import poml.conv.ConvTestCase;

public class ScmTest extends ConvTestCase {

  Scm conv = new Scm();

  @Test public void url() {
    poml.conf.append("scm=");
    poml.conf.append("  url:https://github.com/mamorum/poml/");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "  <scm>" + nl +
      "    <url>https://github.com/mamorum/poml/</url>" + nl +
      "  </scm>" + nl
    );
  }
  
  @Test public void all() {
    poml.conf.append("scm=");
    poml.conf.append("  connect: scm:git:https://github.com/mamorum/poml.git,");
    poml.conf.append("  devConnect: scm:git:git@github.com:mamorum/poml.git,");
    poml.conf.append("  tag: HEAD,");
    poml.conf.append("  url: https://github.com/mamorum/poml/");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "  <scm>" + nl +
      "    <url>https://github.com/mamorum/poml/</url>" + nl +
      "    <connection>scm:git:https://github.com/mamorum/poml.git</connection>" + nl +
      "    <developerConnection>scm:git:git@github.com:mamorum/poml.git</developerConnection>" + nl +
      "    <tag>HEAD</tag>" + nl +
      "  </scm>" + nl
    );
  }

  @Test public void ng_badConf() {
    poml.conf.append("scm=badconf");
    poml.conf.load();
    try { 
      conv.convert(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config");
    }
  }
}
