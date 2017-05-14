package poml.conv.env;

import static org.junit.Assert.fail;

import org.junit.Test;

import poml.conv.ConvTestCase;
import poml.in.Poml;

public class DistTest extends ConvTestCase {

  Dist conv = new Dist();

  @Test public void ossrh() {
    poml = Poml.parse(data(
      "dist=&ossrh"
    ));
    conv.convert(poml, xml);
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
      conv.convert(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config");
    }
  }
}
