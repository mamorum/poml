package poml;

import java.util.HashMap;

import poml.converter.Export;

public class Converters {

  private static HashMap<String, Converter>
    converters = Export.converters();
  
  public static void convert(
    String name, Poml poml, Xml xml
  ) {
    Converter c = converters.get(name);
    if (c == null) throw new RuntimeException(
      "Converter not found for {{" + name + "}}"
    );
    c.convert(poml, xml);
  }
}
