package niji;

import java.util.HashMap;
import java.util.Properties;
import java.util.function.BiConsumer;

import niji.converter.Src;
import niji.converter.func.Prj;
import niji.converter.func.basic.Dist;
import niji.converter.func.basic.Lib;
import niji.converter.func.basic.Pp;

public class Converter {

  private Properties p;
  private Converter(Properties p) { this.p = p; }
  public static Converter of(Properties p) {
    return new Converter(p);
  }
  
  public interface Func extends BiConsumer<Src, StringBuilder> {}
  static HashMap<String, Func> key2func = new HashMap<>();
  static {
    key2func.put(Prj.Start.key, new Prj.Start());
    key2func.put(Prj.End.key, new Prj.End());
    key2func.put(Dist.key, new Dist());
    key2func.put(Lib.key, new Lib());
    key2func.put(Pp.key, new Pp());
  }  
  public void delegate(
    String key, Src src, StringBuilder xml
  ) {
    Func func = key2func.get(key);
    if (func != null) func.accept(src, xml);
    else xml.append("{{" +key + "}}" + System.lineSeparator());
    // TODO temporary ->.
//    throw new RuntimeException(
//      "Property Not Found [template key={{" + "}}]"
//    );
    // <-
  }
  
  public void convert(String tmpl, StringBuilder xml) {
    int start = tmpl.indexOf("{{");
    int end = tmpl.indexOf("}}");
    
    // no conversion
    if (start == -1 || end == -1) {
      xml.append(tmpl);
      xml.append(System.lineSeparator());
      return;
    }
    
    String key = tmpl.substring(start+2, end);
    delegate(
        key, Src.of(p, start), xml
    );
  }
}
