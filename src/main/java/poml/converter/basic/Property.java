package poml.converter.basic;

import java.util.Map;

import poml.Converter;
import poml.Xml;
import poml.Poml;

public class Property implements Converter {

  @Override public String name() { return "property"; }
  
  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> kv = poml.conf.map(name());
    if (kv.size() == 0) return;    
    xml.out.println(sp2 + "<properties>");
    xml.printKvTags(sp4, kv);
    xml.out.println(sp2 + "</properties>");
  }
}
