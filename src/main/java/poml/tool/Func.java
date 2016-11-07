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
    public static void pkg(
      String[] vals, String key, String val
    ) {
      if (vals.length < 3) Throw.badConfig(key, val);
    }
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
  
  public static class Throw {
    public static void noConfig(String key) {
      throw new IllegalStateException(
        "Config not found [key=" + key + "]"
      );
    }
    public static void badConfig(String key, String val) {
      throw new IllegalStateException(
        "Bad config [key=" + key +"] [val=" + val + "]"
      );
    }
  }
}
