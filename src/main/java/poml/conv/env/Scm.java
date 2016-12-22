package poml.conv.env;

import java.util.Map;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;

public class Scm implements Converter {

  @Override public String name() { return "scm"; }
  
  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> map = poml.conf.map(name(), false);
    xml.println("  <scm>");  // -> elements are not required 
    xml.printKvTag(sp4, "url", map.get("url"));
    xml.printKvTag(sp4, "connection", map.get("connect"));
    xml.printKvTag(sp4, "developerConnection", map.get("devConnect"));
    xml.printKvTag(sp4, "tag", map.get("tag"));
    xml.println("  </scm>");
  }
}
