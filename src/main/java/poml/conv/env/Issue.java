package poml.conv.env;

import java.util.Map;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;
import poml.tool.Is;
import poml.tool.Throw;

public class Issue implements Converter {

  @Override public String name() { return "issue"; }

  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> map = poml.conf.map(name(), false);
    if (!Is.in(keys, map)) Throw.noKv(name(), keys);
    xml.println("  <issueManagement>");
    for (String k: keys) {
      xml.printKvTag(sp4, k, map.get(k));
    }
    xml.println("  </issueManagement>");
  }
  private static final String[] keys = {
    "system", "url"  // required
  };
}
