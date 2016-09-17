package poml.converter.basic;

import java.util.Map;

import poml.Converter;
import poml.Dst;
import poml.Src;

public class Prop implements Converter {

  public String key() {return "prop";}
  
  @Override public void convert(Src src, Dst dst) {
    dst.out.println("  <properties>");
    Map<String, String> prop = src.propMap(key());
    for (String k: prop.keySet()) {
      String v = prop.get(k);
      dst.out.print("    ");
      addStartTag(k, dst);
      dst.out.print(v);
      addEndTag(k, dst);
    }
    dst.out.println("  </properties>");
  }

  private void addStartTag(String k, Dst dst) {
    dst.out.print("<");
    dst.out.print(k);
    dst.out.print(">");
  }  
  private void addEndTag(String k, Dst dst) {
    dst.out.print("</");
    dst.out.print(k);
    dst.out.println(">");
  }
}
