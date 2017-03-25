package poml.conv.env;

import java.util.Map;

import poml.conv.Converter;
import poml.io.Poml;
import poml.io.Xml;
import poml.tool.Throw;

public class Dist implements Converter {
    
  @Override public String name() { return "dist"; }
    
  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> map = poml.conf.map(name(), false);    
    xml.out.add("  <distributionManagement>").nl();
    printOssrh("snap", map, snap, xml);
    printOssrh("repo", map, repo, xml);
    xml.out.add("  </distributionManagement>").nl();
  }
  
  private final void printOssrh(
    String key, Map<String, String> map,
    String[] lines, Xml xml
  ) {
    String val = map.get(key);
    if (val == null) return;
    else if ("ossrh".equals(val)) xml.out.add(lines);
    else Throw.badConf(name(), key + ":" + val);
  }
  private static final String[] snap = {
    "    <snapshotRepository>",
    "      <id>ossrh</id>",
    "      <url>https://oss.sonatype.org/content/repositories/snapshots</url>",
    "    </snapshotRepository>"
  };
  private static final String[] repo = {
    "    <repository>",
    "      <id>ossrh</id>",
    "      <url>https://oss.sonatype.org/service/local/staging/deploy/maven2/</url>",
    "    </repository>"
  };
}
