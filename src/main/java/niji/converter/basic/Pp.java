package niji.converter.basic;

import java.util.Map;

import niji.Dst;
import niji.Src;
import niji.Converters.Converter;

public class Pp implements Converter {

  public static String key = "pp";
  
  @Override public void convert(Src src, Dst dst) {
    dst.out.println("  <properties>");
    Map<String, String> prop = src.pMap(key);
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
