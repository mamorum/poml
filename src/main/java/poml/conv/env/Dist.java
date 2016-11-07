package poml.conv.env;

import java.util.Map;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;
import poml.tool.Func.Throw;

public class Dist implements Converter {
    
  @Override public String name() { return "dist"; }
    
  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> map = poml.conf.map(name(), false);    
    xml.out.println("  <distributionManagement>");
    printOssrh("snap", map, snap, xml);
    printOssrh("repo", map, repo, xml);
    xml.out.println("  </distributionManagement>");
  }
  
  private final void printOssrh(
    String key, Map<String, String> map,
    String[] lines, Xml xml
  ) {
    String val = map.get(key);
    if (val == null) return;
    else if ("ossrh".equals(val)) xml.print(lines);
    else Throw.badConfig(name(), key + ":" + val);
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
