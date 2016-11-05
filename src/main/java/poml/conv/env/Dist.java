package poml.conv.env;

import java.util.Map;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;

public class Dist implements Converter {
    
  @Override public String name() { return "dist"; }
    
  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> map = poml.conf.map(name(), false);
    if (map.size() == 0) return;
    
    xml.out.println("  <distributionManagement>");
    printOssrh(map.get("snap"), xml, snap);
    printOssrh(map.get("repo"), xml, repo);
    xml.out.println("  </distributionManagement>");
  }
  
  private static final void printOssrh(
    String val, Xml xml, String[] lines
  ) {
    if ("ossrh".equals(val)) xml.print(lines);
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
