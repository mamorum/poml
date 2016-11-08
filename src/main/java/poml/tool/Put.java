package poml.tool;

import java.util.Map;

public class Put {
  public static <K, V> void defaults(
    K k, V v, Map<K, V> to
  ) {
    if (to.containsKey(k)) return;
    to.put(k, v);
  }
}

