package niji.template;

import java.util.HashMap;

import niji.template.converter.Prj;
import niji.template.converter.basic.Dist;
import niji.template.converter.basic.Lib;
import niji.template.converter.basic.Pp;
import niji.template.converter.build.plugin.Javac;

public class Converters {

  public static Converter get(String key) {
    Converter c = key2func.get(key);
    if (c == null) return new Converter() {
      @Override public void toXml(
          Src src, StringBuilder xml
      ) {
        xml.append("{{NoConverter}}");
        xml.append(System.lineSeparator());
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
    key2func.put(Javac.key, new Javac());
  }
}
