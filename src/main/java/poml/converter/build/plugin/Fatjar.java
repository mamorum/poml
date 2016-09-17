package poml.converter.build.plugin;

import java.util.Map;

import poml.Converter;
import poml.Dst;
import poml.Src;
import poml.lib.Mst;

public class Fatjar implements Converter {
  
  public String key() {return "fatjar";}
  
  @Override public void convert(Src src, Dst dst) {
    // prepare default value.
    Map<String, String> map = src.propMap(key());
    if (map.get("ver") == null) map.put("ver", "2.6");
    Mst.render("fatjar.mustache", map, dst.out);
  }
}
