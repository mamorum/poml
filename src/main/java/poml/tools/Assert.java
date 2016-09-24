package poml.tools;

import java.util.Map;

public class Assert {
  public static void exists(
    String k, Map<String, String> in, String cname
  ) {
    if (in.get(k) == null) throw new RuntimeException(
      "[ERROR] \"" +
      k + "\" is required in \"" + cname + "\" property."
    );
  }
  public static void exists(
    String[] k, Map<String, String> in, String cname
  ) {
    for (String key: k) exists(key, in, cname);
  }
}
