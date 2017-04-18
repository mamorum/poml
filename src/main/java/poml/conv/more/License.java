package poml.conv.more;

import java.util.Map;

import poml.conv.Converter;
import poml.io.Poml;
import poml.io.Xml;

public class License implements Converter{

  @Override public String name() { return "license"; }
  
  @Override public void convert(Poml poml, Xml xml) {
    for (String lic: poml.conf.vals(name())) {
      xml.out.add("    <license>").nl();
      if ("&apache2".equals(lic)) xml.out.add(apache2);
      else if ("&mit".equals(lic)) xml.out.add(mit);
      else addLicense(poml.conf.map(lic, false), xml);
      xml.out.add("    </license>").nl();
    }
  }
  private void addLicense(Map<String, String> conf, Xml xml) {
    for (String k: keys) xml.outTag(sp6, k, conf.get(k));
  }
  private static String[] keys = {
    "name", "url", "distribution", "comments"
  };
  private static final String[]
    apache2 = {
      "      <name>The Apache License, Version 2.0</name>",
      "      <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>"
  }, mit = {
      "      <name>MIT License</name>",
      "      <url>https://opensource.org/licenses/MIT</url>"
  };
}
