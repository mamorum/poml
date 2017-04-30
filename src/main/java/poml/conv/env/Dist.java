package poml.conv.env;

import poml.conv.Converter;
import poml.io.Poml;
import poml.io.Xml;
import poml.util.Throw;

public class Dist implements Converter {
  @Override public String name() { return "dist"; }

  @Override public void convert(Poml in, Xml out) {
    String val = in.conf.val(name());
    if ("&ossrh".equals(val)) ossrh(out);
    else Throw.badConf(name(), val);
  }
  private final void ossrh(Xml out) {
    out.line("  <distributionManagement>");
    out.line("    <snapshotRepository>");
    out.line("      <id>ossrh</id>");
    out.line("      <url>https://oss.sonatype.org/content/repositories/snapshots</url>");
    out.line("    </snapshotRepository>");
    out.line("    <repository>");
    out.line("      <id>ossrh</id>");
    out.line("      <url>https://oss.sonatype.org/service/local/staging/deploy/maven2/</url>");
    out.line("    </repository>");
    out.line("  </distributionManagement>");
  }
}
