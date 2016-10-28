package poml.converter.basic;

import java.util.Map;

import poml.Converter;
import poml.in.Poml;
import poml.out.Xml;

public class Property implements Converter {

  @Override public String name() { return "property"; }
  
  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> kv = poml.conf.map(name());
    if (kv.size() == 0) return;    
    xml.out.println("  <properties>");
    xml.printKvTags(sp4, kv);
    xml.out.println("  </properties>");
  }
}
