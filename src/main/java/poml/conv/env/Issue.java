package poml.conv.env;

import java.util.Map;

import poml.conv.Converter;
import poml.io.Poml;
import poml.io.Xml;
import poml.tool.Is;
import poml.tool.Throw;

public class Issue implements Converter {

  @Override public String name() { return "issue"; }

  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> map = poml.conf.map(name(), false);
    if (!Is.in(keys, map)) Throw.noKv(name(), keys);
    xml.out.add("  <issueManagement>").nl();
    for (String k: keys) {
      xml.outTag(sp4, k, map.get(k));
    }
    xml.out.add("  </issueManagement>").nl();
  }
  private static final String[] keys = {
    "system", "url"  // required
  };
}
