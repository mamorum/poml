package poml.conv.basic;

import java.util.Map;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;

public class Properties implements Converter {
  public static final String name = "properties",
      enc="&encoding", comp="&compiler";

  @Override public String name() { return name; }

  @Override public void convert(Poml in, Xml out) {
    Map<String, String> kv = in.conf.map(name());
    out.line("  <properties>");
    for (String k: kv.keySet()) {
      render(out, k, kv.get(k));
    }
    out.line("  </properties>");
  }
  private void render(Xml out, String k, String v) {
    if (enc.equals(k)) {
      out.tag(sp4, "project.build.sourceEncoding", v);
      out.tag(sp4, "project.reporting.outputEncoding", v);
    }
    else if (comp.equals(k)) {
      out.tag(sp4, "maven.compiler.source", v);
      out.tag(sp4, "maven.compiler.target", v);
    }
    else out.tag(sp4, k, v);
  }
}
