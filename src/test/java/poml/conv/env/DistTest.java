package poml.conv.env;

import static org.junit.Assert.fail;

import org.junit.Test;

import poml.conv.ConvTestCase;
import poml.conv.env.Dist;

public class DistTest extends ConvTestCase {

  Dist conv = new Dist();

  @Test public void ossrh_snap_repo() {
    poml.conf.parse(in(
      "dist=snap:ossrh, repo:ossrh"
    ));
    conv.convert(poml, xml);
    output.is(
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
  
  @Test public void ossrh_snap() {
    poml.conf.parse(in(
      "dist=snap:ossrh"
    ));
    conv.convert(poml, xml);
    output.is(
      "  <distributionManagement>" + nl + 
      "    <snapshotRepository>" + nl +
      "      <id>ossrh</id>" + nl +
      "      <url>https://oss.sonatype.org/content/repositories/snapshots</url>" + nl +
      "    </snapshotRepository>" + nl +
      "  </distributionManagement>" + nl
    );
  }

  @Test public void ng_badConf() {
    poml.conf.parse(in(
      "dist=snap:ossrh, repo:oss"
    ));
    try { 
      conv.convert(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config");
    }
  }
}
