package poml.conv.more;

import java.util.Map;

import poml.conv.Converter;
import poml.io.Poml;
import poml.io.Xml;

public class Info implements Converter {
  @Override public String name() { return "info"; }

  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> map = poml.conf.map(name());
    xml.outTag(sp2, name, map.get(name));
    xml.outTag(sp2, desc, map.get(desc));
    xml.outTag(sp2, url, map.get(url));
    xml.outTag(sp2, year, map.get(year));
  }
  private static final String
    name = "name", desc = "description",
    url = "url", year = "inceptionYear";
}
