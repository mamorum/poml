package poml;

import java.util.Map;

public interface Converter {

  String name();
  void convert(Poml poml, Xml xml);

  public static String nl = System.lineSeparator();
  public static String
    sp2="  ",
    sp4=sp2+sp2,
    sp6=sp2+sp4,
    sp8=sp2+sp6;

  public static class Put {
    public static void defaults(
      String k, String v,
      Map<String, String> to
    ) {
      if (to.get(k) == null) to.put(k, v);
    }
  }
}

