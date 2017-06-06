package poml.convert;

import static org.junit.Assert.fail;

import org.junit.Test;

import poml.UtCase;

public class EnvDistTest extends UtCase {

  @Test public void ossrh() {
    poml(
      "dist=&ossrh"
    );
    Env.dist(poml, xml);
    xml(
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

  @Test public void ng_val() {
    poml("dist=oss");
    try {
      Env.dist(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      err("Invalid config [key=dist] [val=oss]", e);
    }
  }
}
