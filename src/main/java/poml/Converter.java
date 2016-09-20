package poml;

import java.util.Map;

public abstract class Converter {

  public abstract String name();
  public abstract void convert(Src src, Dst dst);
  
  public static String nl = System.lineSeparator();
  public static String
    sp2="  ",
    sp4=sp2+sp2,
    sp6=sp2+sp4,
    sp8=sp2+sp6,
    sp10=sp2+sp8;

  // utilities -->
  public void putDefault(
      String k, String v, Map<String, String> to
  ) {
    if (to.get(k) == null) to.put(k, v);
  }
  
  public void printKvTags(
    String space, Map<String, String> kv, Dst dst
  ) {
    for (String k: kv.keySet()) {
      dst.out.print(space);
      dst.out.println(
        kvTagTmpl
          .replace("{{k}}", k) 
          .replace("{{v}}", kv.get(k))
      );
    }
  }
  public static final String kvTagTmpl
    = "<{{k}}>{{v}}</{{k}}>";
}

