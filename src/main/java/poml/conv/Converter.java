package poml.conv;

import poml.io.Poml;
import poml.io.Xml;

public interface Converter {

  String name();
  void convert(Poml poml, Xml xml);

  public static String
    sp2="  ",
    sp4=sp2+sp2,
    sp6=sp2+sp4,
    sp8=sp2+sp6,
    sp10=sp2+sp8;
}

