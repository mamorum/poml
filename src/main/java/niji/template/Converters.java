package niji.template;

import java.util.HashMap;

import niji.template.converter.Prj;
import niji.template.converter.basic.Dist;
import niji.template.converter.basic.Lib;
import niji.template.converter.basic.Pp;

public class Converters {

  public static Converter get(String key) {
    return key2func.get(key);
  }

  static HashMap<String, Converter> key2func = new HashMap<>();
  static {
    key2func.put(Prj.Start.key, new Prj.Start());
    key2func.put(Prj.End.key, new Prj.End());
    key2func.put(Dist.key, new Dist());
    key2func.put(Lib.key, new Lib());
    key2func.put(Pp.key, new Pp());
  }
}
