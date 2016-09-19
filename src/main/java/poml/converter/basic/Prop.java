package poml.converter.basic;

import java.util.Map;

import poml.Converter;
import poml.Dst;
import poml.Src;

public class Prop implements Converter {

  public String key() { return "prop"; }
  
  private static final String tmpl 
    = "    <{{key}}>{{val}}</{{key}}>";
  
  @Override public void convert(Src src, Dst dst) {
    dst.out.println("  <properties>");
    Map<String, String> prop = src.propMap(key());
    for (String k: prop.keySet()) {
      String v = prop.get(k);
      dst.out.println(
         tmpl.replace(
           "{{key}}", k
         ).replace("{{val}}", v)
      );
    }
    dst.out.println("  </properties>");
  }
}
