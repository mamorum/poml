package poml.conv.more;

import java.util.Map;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;

public class Info implements Converter {
  @Override public String name() { return "info"; }

  @Override public void convert(Poml in, Xml out) {
    Map<String, String> kv = in.conf.map(name());
    out.tag(sp2, name, kv.get(name));
    out.tag(sp2, desc, kv.get(desc));
    out.tag(sp2, url, kv.get(url));
    out.tag(sp2, year, kv.get(year));
  }
  private static final String
    name = "name", desc = "description",
    url = "url", year = "inceptionYear";
}