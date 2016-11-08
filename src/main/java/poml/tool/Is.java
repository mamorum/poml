package poml.tool;

import java.util.Map;

public class Is {
  public static boolean pkg(String[] vals) {
    return (vals.length >= 3);
  }
  public static boolean in(
    String[] keys, Map<String, String> in
  ) {
    for (String k: keys) {
      if (!in.containsKey(k)) return false;
    }
    return true;
  }
}
