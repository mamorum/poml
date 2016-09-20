package poml.converter.basic;

import java.util.Map;

import poml.Converter;
import poml.Dst;
import poml.Src;

public class Prop extends Converter {

  public String key() { return "prop"; }
  
  @Override public void convert(Src src, Dst dst) {
    Map<String, String> prop = src.propMap(key());
    if (prop.size() == 0) return;    
    dst.out.println("  <properties>");
    dst.out.print(kvTags(sp4, prop));
    dst.out.println("  </properties>");
  }
}
