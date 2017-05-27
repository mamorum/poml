package poml.conv.env;

import static org.junit.Assert.fail;

import org.junit.Test;

import poml.Poml;
import poml.conv.ConvTestCase;
import poml.convert.Env;

public class DistTest extends ConvTestCase {

  @Test public void ossrh() {
    poml = Poml.parse(data(
      "dist=&ossrh"
    ));
    Env.dist(poml, xml);
    result(
      "  <distributionManagement>" + nl +
      "    <snapshotRepository>" + nl +
      "      <id>ossrh</id>" + nl +
      "      <url>https://oss.sonatype.org/content/repositories/snapshots</url>" + nl +
      "    </snapshotRepository>" + nl +
      "    <repository>" + nl +
      "      <id>ossrh</id>" + nl +
      "      <url>https://oss.sonatype.org/service/local/staging/deploy/maven2/</url>" + nl +
      "    </repository>" + nl +
      "  </distributionManagement>" + nl
    );
  }

  @Test public void ng_badConf() {
    poml = Poml.parse(data(
      "dist=oss"
    ));
    try {
      Env.dist(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config");
    }
  }
}
