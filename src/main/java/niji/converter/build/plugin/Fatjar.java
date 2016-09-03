package niji.converter.build.plugin;

import java.util.Map;

import niji.Converters.Converter;
import niji.Dst;
import niji.Src;
import niji.lib.Mst;

public class Fatjar implements Converter {
  
  public static String key = "fatjar";
  
  @Override public void convert(Src src, Dst dst) {
    // prepare default value.
    Map<String, String> map = src.pMap(key);
    if (map.get("ver") == null) map.put("ver", "2.6");
    Mst.render("fatjar.mustache", map, dst.out);
  }
}
