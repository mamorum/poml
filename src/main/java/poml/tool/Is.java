package poml.tool;

import java.util.Map;

public class Is {
  public static boolean pkg(String[] vals) {
    return (vals.length >= 3);
  }
  public static boolean lib(String[] vals) {
    return (vals.length >= 2);
  }
  public static boolean in(
    String k, Map<String, String> in
  ) {
    return in.containsKey(k);
  }
}
