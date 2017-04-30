package poml.conv.env;

import java.util.Map;

import poml.conv.Converter;
import poml.io.Poml;
import poml.io.Xml;

public class Scm implements Converter {
  @Override public String name() { return "scm"; }

  @Override public void convert(Poml in, Xml out) {
    Map<String, String> kv = in.conf.map(name());
    out.line("  <scm>");
    out.tag(sp4, url, kv.get(url));
    out.tag(sp4, con, kv.get(con));
    out.tag(sp4, dev, kv.get(dev));
    out.tag(sp4, tag, kv.get(tag));
    out.line("  </scm>");
  }
  private static final String
    url = "url", con = "connection",
    dev = "developerConnection", tag = "tag";
}
