package poml;

import java.util.HashMap;

import poml.converter.Export;

public class Converters {

  private static final HashMap<String, Converter>
    converters = Export.converters();
  
  public static Converter get(String name) {
    Converter c = converters.get(name);
    if (c == null) throw new RuntimeException(
      "Converter not found for {{" + name + "}}"
    );
    return c;
  }
}
