package poml;

import java.util.HashMap;

import poml.converter.Export;

public class Converters {

  private static HashMap<String, Converter>
    converters = Export.converters();
  
  public static void convert(String key, Poml src, Pom dst) {
    Converter c = converters.get(key);
    if (c == null) throw new RuntimeException(
      "Converter not found for {{" + key + "}}"
    );
    c.convert(src, dst);
  }
}
