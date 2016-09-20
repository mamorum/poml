package poml;

import java.util.Map;

public abstract class Converter {

  public abstract String key();
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
  
  public String kvTags(
    String space, Map<String, String> kv
  ) {
    StringBuilder buf = new StringBuilder();
    String renderd;
    for (String k: kv.keySet()) {
      renderd = kvTagTmpl
          .replace("{{k}}", k) 
          .replace("{{v}}", kv.get(k));
      buf.append(space);
      buf.append(renderd);
      buf.append(nl);
    }
    return buf.toString();
  }
  public static final String kvTagTmpl
    = "<{{k}}>{{v}}</{{k}}>";
}

