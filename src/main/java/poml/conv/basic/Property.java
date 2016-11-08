package poml.conv.basic;

import java.util.Map;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;

public class Property implements Converter {

  @Override public String name() { return "property"; }
  
  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> kv = poml.conf.map(name(), false);
    xml.println("  <properties>");
    xml.printKvTags(sp4, kv);
    xml.println("  </properties>");
  }
}
