package poml.converter.basic;

import java.util.Map;

import poml.Converter;
import poml.Dst;
import poml.Src;

public class Prop extends Converter {

  public String name() { return "prop"; }
  
  @Override public void convert(Src src, Dst dst) {
    Map<String, String> prop = src.propMap(name());
    if (prop.size() == 0) return;    
    dst.out.println("  <properties>");
    printKvTags(sp4, prop, dst);
    dst.out.println("  </properties>");
  }
}
