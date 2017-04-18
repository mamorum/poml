package poml.conv.env;

import java.util.Map;

import poml.conv.Converter;
import poml.io.Poml;
import poml.io.Xml;

public class Scm implements Converter {
  @Override public String name() { return "scm"; }
  
  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> map = poml.conf.map(name());
    xml.out.add("  <scm>").nl(); 
    xml.outTag(sp4, url, map.get(url));
    xml.outTag(sp4, con, map.get(con));
    xml.outTag(sp4, dev, map.get(dev));
    xml.outTag(sp4, tag, map.get(tag));
    xml.out.add("  </scm>").nl();
  }
  private static final String
    url = "url", con = "connection",
    dev = "developerConnection", tag = "tag";
}
