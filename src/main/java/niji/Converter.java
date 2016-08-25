package niji;

import java.util.HashMap;
import java.util.Properties;
import java.util.function.Function;

import niji.converter.basic.Dist;
import niji.converter.basic.Lib;

public class Converter {

  static HashMap<String, Function<String, String>>
    key2func = new HashMap<>();
  static {
    key2func.put("dist", new Dist());
    key2func.put("lib", new Lib());
  }
  
  public static String tags(String key, Properties p) {
    String v = p.getProperty(key);
    Function<String, String> func = key2func.get(key);
    if (func != null) return func.apply(v);
    throw new RuntimeException(
      "Property Not Found [template key={{" + "}}]"
    );
  }
}
