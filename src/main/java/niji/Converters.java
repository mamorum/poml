package niji;

import java.util.HashMap;

import niji.converter.Prj;
import niji.converter.basic.Dist;
import niji.converter.basic.Lib;
import niji.converter.basic.Pp;
import niji.converter.build.plugin.Compile;

public class Converters {

  @FunctionalInterface
  public static interface Converter {
    void convert(Src src, Dst dst);
  }
  
  public static Converter get(String key) {
    Converter c = key2func.get(key);
    if (c == null) return new Converter() {
      @Override public void convert(
          Src src, Dst dst
      ) {
        dst.out.println("{{NoConverter}}");
      }
    };
    // TODO above, change to ->.
//  throw new RuntimeException(
//    "Property Not Found [template key={{" + "}}]"
//  );
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
  }
}
