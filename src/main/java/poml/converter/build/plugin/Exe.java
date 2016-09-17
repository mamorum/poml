package poml.converter.build.plugin;

import java.util.Map;

import poml.Converter;
import poml.Dst;
import poml.Src;
import poml.lib.Mst;

public class Exe implements Converter {
  
  public String key() {return "exe";}
  
  @Override public void convert(Src src, Dst dst) {
    // prepare default value.
    Map<String, String> map = src.propMap(key());
    if (map.get("ver") == null) map.put("ver", "1.5.0");
    Mst.render("exe.mustache", map, dst.out);
  }
}
