package poml.tool;

import java.util.Map;

public class Func {
  public static class Put {
    public static <K, V> void defaults(
      K k, V v, Map<K, V> to
    ) {
      if (to.containsKey(k)) return;
      to.put(k, v);
    }
  }

  public static class Assert {
    public static void notNull(
      String k, Map<String, String> in, String cname
    ) {
      if (in.get(k) == null) throw new RuntimeException(
        "\"" + cname + "\" requires \"" + k + "\"" +
        " in property section"
      );
    }
    public static void notNull(
      String[] k, Map<String, String> in, String cname
    ) {
      for (String key: k) notNull(key, in, cname);
    }
  }
}
