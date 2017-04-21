package poml.conv.env;

import poml.conv.Converter;
import poml.io.Poml;
import poml.io.Xml;
import poml.util.Throw;

public class Dist implements Converter {
  @Override public String name() { return "dist"; }
    
  @Override public void convert(Poml poml, Xml xml) {
    String v = poml.conf.val(name());
    if ("&ossrh".equals(v)) render(xml);
    else Throw.badConf(name(), v);
  }
  private final void render(Xml xml) {
    xml.out
      .add("  <distributionManagement>").nl()
      .add("    <snapshotRepository>").nl()
      .add("      <id>ossrh</id>").nl()
      .add("      <url>https://oss.sonatype.org/content/repositories/snapshots</url>").nl()
      .add("    </snapshotRepository>").nl()
      .add("    <repository>").nl()
      .add("      <id>ossrh</id>").nl()
      .add("      <url>https://oss.sonatype.org/service/local/staging/deploy/maven2/</url>").nl()
      .add("    </repository>").nl()
      .add("  </distributionManagement>").nl();
  };
}
