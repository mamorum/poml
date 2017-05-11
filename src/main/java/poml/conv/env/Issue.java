package poml.conv.env;

import java.util.Map;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;

public class Issue implements Converter {
  @Override public String name() { return "issue"; }

  @Override public void convert(Poml in, Xml out) {
    Map<String, String> kv = in.conf.map(name());
    out.line("  <issueManagement>");
    out.tag(sp4, sys, kv.get(sys));
    out.tag(sp4, url, kv.get(url));
    out.line("  </issueManagement>");
  }
  private static final String sys = "system", url = "url";
}
