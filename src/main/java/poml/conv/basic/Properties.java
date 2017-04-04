package poml.conv.basic;

import java.util.Map;

import poml.conv.Converter;
import poml.io.Poml;
import poml.io.Xml;

public class Properties implements Converter {

  @Override public String name() { return "properties"; }
  
  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> kv = poml.conf.map(name(), false);
    xml.out.add("  <properties>").nl();
    for (String k: kv.keySet()) {
      if (k.startsWith("&")) replace(k, kv.get(k), xml);
      else xml.outTag(sp4, k, kv.get(k));
    }
    xml.out.add("  </properties>").nl();
  }
  
  private void replace(String k, String v, Xml xml) {
    if ("&encoding".equals(k)) {
      xml.outTag(sp4, "project.build.sourceEncoding", v);
      xml.outTag(sp4, "project.reporting.outputEncoding", v);
    }
    else if ("&compiler".equals(k)) {
      xml.outTag(sp4, "maven.compiler.source", v);
      xml.outTag(sp4, "maven.compiler.target", v);
    }
  }
}
