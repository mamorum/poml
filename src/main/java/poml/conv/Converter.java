package poml.conv;

import poml.io.Poml;
import poml.io.Xml;

public interface Converter {
  String name();
  void convert(Poml poml, Xml xml);

  static final String
    sp2="  ", sp4="    ", sp6="      ",
    sp8="        ", sp10="          ";
}

