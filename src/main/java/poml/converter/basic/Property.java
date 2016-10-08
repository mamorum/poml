package poml.converter.basic;

import java.util.Map;

import poml.Converter;
import poml.Dst;
import poml.Src;

public class Property implements Converter {

  @Override public String name() { return "property"; }
  
  @Override public void convert(Src src, Dst dst) {
    Map<String, String> kv = src.conf.map(name());
    if (kv.size() == 0) return;    
    dst.out.println(sp2 + "<properties>");
    dst.printKvTags(sp4, kv);
    dst.out.println(sp2 + "</properties>");
  }
}
