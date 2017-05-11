package poml.conv.basic;

import java.util.Map;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;

public class Properties implements Converter {
  @Override public String name() { return "properties"; }

  @Override public void convert(Poml in, Xml out) {
    Map<String, String> kv = in.conf.map(name());
    out.line("  <properties>");
    for (String k: kv.keySet()) {
      render(out, k, kv.get(k));
    }
    out.line("  </properties>");
  }
  private void render(Xml out, String k, String v) {
    if ("&encoding".equals(k)) {
      out.tag(sp4, "project.build.sourceEncoding", v);
      out.tag(sp4, "project.reporting.outputEncoding", v);
    }
    else if ("&compiler".equals(k)) {
      out.tag(sp4, "maven.compiler.source", v);
      out.tag(sp4, "maven.compiler.target", v);
    }
    else out.tag(sp4, k, v);
  }
}
