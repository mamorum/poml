package poml.converter.basic;

import java.util.Map;

import poml.Converter;
import poml.Pom;
import poml.Poml;

public class Property implements Converter {

  @Override public String name() { return "property"; }
  
  @Override public void convert(Poml poml, Pom pom) {
    Map<String, String> kv = poml.conf.map(name());
    if (kv.size() == 0) return;    
    pom.out.println(sp2 + "<properties>");
    pom.printKvTags(sp4, kv);
    pom.out.println(sp2 + "</properties>");
  }
}
