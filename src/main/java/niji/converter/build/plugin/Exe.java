package niji.converter.build.plugin;

import java.util.Map;

import niji.Converters.Converter;
import niji.Dst;
import niji.Src;
import niji.lib.Mst;

public class Exe implements Converter {
  
  public String key() {return "exe";}
  
  @Override public void convert(Src src, Dst dst) {
    // prepare default value.
    Map<String, String> map = src.pMap(key());
    if (map.get("ver") == null) map.put("ver", "1.5.0");
    Mst.render("exe.mustache", map, dst.out);
  }
}
