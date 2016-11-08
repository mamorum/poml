package poml.conv.more;

import java.util.Map;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;
import poml.tool.Throw;

public class Info implements Converter {

  @Override public String name() { return "info"; }

  private static final String[] tags =
    {"name", "description", "url", "inceptionYear"};

  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> map = poml.conf.map(name(), false);
    // name - inceptionYear
    for (String t: tags) {
      xml.printKvTag(sp2, t, map.get(t));
    }
    // license
    String lic = map.get("license");
    if (lic == null) return;
    if ("Apache 2.0".equals(lic)) xml.print(apache2);
    else if ("MIT".equals(lic)) xml.print(mit);
    else Throw.badConf(name(), "license:" + lic);
  }

  private static final String[] mit = {
    "  <licenses>",
    "    <license>",
    "      <name>MIT License</name>",
    "      <url>http://www.opensource.org/licenses/mit-license.php</url>",
    "    </license>",
    "  </licenses>"
  }; 
  private static final String[] apache2 = {
    "  <licenses>",
    "    <license>",
    "      <name>The Apache License, Version 2.0</name>",
    "      <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>",
    "    </license>",
    "  </licenses>"
  };
}
