package poml.conv.more;

import java.util.Map;

import poml.conv.Converter;
import poml.io.Poml;
import poml.io.Xml;

public class Info implements Converter {

  @Override public String name() { return "info"; }

  private static final String[] keys =
    {"name", "description", "url", "inceptionYear"};

  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> map = poml.conf.map(name(), false);
    for (String k: keys) xml.outTag(sp2, k, map.get(k));
  }
}
