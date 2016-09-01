package niji.template;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class Src {
  public Properties p;
  public int indent;
  
  private Src(Properties p, int indent) {
    this.p = p; this.indent = indent;
  }
  public static Src of(Properties p, int indent) {
    return new Src(p, indent);
  }
  
  public String property(String key) {
    return p.getProperty(key);
  }
  public String[] propertyList(String key) {
    String pp = property(key);
    if (pp == null) return null;
    else return pp.split(",");
  }
  public Map<String, String> propertyMap(String key) {
    Map<String, String> map = new LinkedHashMap<>();
    for (String pp :propertyList(key)) {
      String[] kv = pp.split(":");
      String k = kv[0].trim();
      String v = kv[1].trim();
      map.put(k, v);
    }
    return map;
  }
}
