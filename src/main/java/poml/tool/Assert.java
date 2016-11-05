package poml.tool;

import java.util.Map;

public class Assert {
  public static void exist(
    String k, Map<String, String> in, String cname
  ) {
    if (in.get(k) == null) throw new RuntimeException(
      "\"" + cname + "\" requires \"" + k + "\"" +
      " in property section"
    );
  }
  public static void exists(
    String[] k, Map<String, String> in, String cname
  ) {
    for (String key: k) exist(key, in, cname);
  }
}
