package poml.converter.build.plugin;

import java.util.Map;

import poml.Converter;
import poml.Dst;
import poml.Src;
import poml.lib.Mst;

public class Compile implements Converter {
  
  public String key() {return "compile";}
  
  @Override public void convert(Src src, Dst dst) {
    // prepare default value.
    Map<String, String> map = src.propMap(key());
    if (map.get("ver") == null) map.put("ver", "3.5.1");
    Mst.render("compile.mustache", map, dst.out);
  }
}
