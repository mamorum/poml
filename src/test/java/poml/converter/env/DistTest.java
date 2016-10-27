package poml.converter.env;

import org.junit.Test;

import poml.ConverterCase;

public class DistTest extends ConverterCase {

  Dist conv = new Dist();

  @Test public void ossrh_snap_repo() {
    poml.conf.append("dist=snap:ossrh, repo:ossrh");
    poml.conf.load();
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
    poml.conf.append("dist=snap:ossrh");
    poml.conf.load();
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
}
