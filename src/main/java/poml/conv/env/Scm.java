package poml.conv.env;

import java.util.Map;

import poml.conv.Converter;
import poml.io.Poml;
import poml.io.Xml;

public class Scm implements Converter {

  @Override public String name() { return "scm"; }
  
  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> map = poml.conf.map(name(), false);
    xml.out.add("  <scm>").nl();  // -> elements are not required 
    xml.outTag(sp4, url, map.get(url));
    xml.outTag(sp4, "connection", map.get("connect"));
    xml.outTag(sp4, "developerConnection", map.get("devConnect"));
    xml.outTag(sp4, tag, map.get(tag));
    xml.out.add("  </scm>").nl();
  }
  private static final String url = "url", tag = "tag";
}
