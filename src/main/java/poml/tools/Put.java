package poml.tools;

import java.util.Map;

public class Put {
  public static void defaults(
    String k, String v,
    Map<String, String> to
  ) {
    if (to.get(k) == null) to.put(k, v);
  }
}
