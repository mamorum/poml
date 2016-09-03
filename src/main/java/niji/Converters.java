package niji;

import java.util.HashMap;

import niji.converter.Prj;
import niji.converter.basic.Dist;
import niji.converter.basic.Lib;
import niji.converter.basic.Pp;
import niji.converter.build.plugin.Compile;
import niji.converter.build.plugin.Exe;
import niji.converter.build.plugin.Fatjar;

public class Converters {

  @FunctionalInterface
  public static interface Converter {
    void convert(Src src, Dst dst);
  }
  
  public static Converter get(String key) {
    Converter c = key2func.get(key);
    if (c == null) throw new RuntimeException(
      "Property Not Found [template key={{" + key + "}}]"
    );
    return c;
  }

  static HashMap<String, Converter>
    key2func = new HashMap<>();
  static {
    key2func.put(Prj.Start.key, new Prj.Start());
    key2func.put(Prj.End.key, new Prj.End());
    key2func.put(Dist.key, new Dist());
    key2func.put(Lib.key, new Lib());
    key2func.put(Pp.key, new Pp());
    key2func.put(Compile.key, new Compile());
    key2func.put(Fatjar.key, new Fatjar());
    key2func.put(Exe.key, new Exe());
  }
}
