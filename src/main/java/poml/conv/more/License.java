package poml.conv.more;

import java.util.Map;

import poml.conv.Converter;
import poml.io.Poml;
import poml.io.Xml;

public class License implements Converter{
  @Override public String name() { return "license"; }

  @Override public void convert(Poml in, Xml out) {
    String[] lics = in.conf.vals(name());
    for (String lic: lics) {
      out.line("    <license>");
      if ("&apache2".equals(lic)) apache2(out);
      else if ("&mit".equals(lic)) mit(out);
      else $license(lic, in, out);
      out.line("    </license>");
    }
  }
  private void apache2(Xml out) {
    out.line("      <name>The Apache License, Version 2.0</name>");
    out.line("      <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>");
  }
  private void mit(Xml out) {
    out.line("      <name>MIT License</name>");
    out.line("      <url>https://opensource.org/licenses/MIT</url>");
  }
  private void $license(String lic, Poml in, Xml out) {
    Map<String, String> kv = in.conf.map(lic);
    out.tag(sp6, name, kv.get(name));
    out.tag(sp6, url, kv.get(url));
    out.tag(sp6, dist, kv.get(dist));
    out.tag(sp6, come, kv.get(come));
  }
  private static final String
    name="name", url="url", dist="distribution", come="comments";
}
