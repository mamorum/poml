package niji;

import java.util.HashMap;

import org.reflections.Reflections;

public class Converters {

  public static interface Converter {
    String key();
    void convert(Src src, Dst dst);
  }
  
  public static void convert(String key, Src src, Dst dst) {
    get(key).convert(src, dst);
  }
  
  private static Converter get(String key) {
    Converter c = converters.get(key);
    if (c == null) throw new RuntimeException(
      "Property not found for {{" + key + "}}"
    );
    return c;
  }

  private static HashMap<String, Converter>
    converters = new HashMap<>();
  static { init(); }
  
  private static void init() {
    Reflections r = new Reflections("niji.converter");
    r.getSubTypesOf(Converter.class).forEach((c) -> {
      try {
        Converter obj = c.newInstance();
        converters.put(obj.key(), obj);
      } catch (
          InstantiationException |
          IllegalAccessException e
      ) {
        throw new RuntimeException(e);
      }
    });
  }
}
