package poml.conv.basic;

import java.util.Map;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;

public class Property implements Converter {

  @Override public String name() { return "property"; }
  
  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> kv = poml.conf.map(name(), false);
    xml.println("  <properties>");
    for (String k: kv.keySet()) {
      if (k.startsWith("$")) replace(k, kv.get(k), xml);
      else xml.printKvTag(sp4, k, kv.get(k));
    }
    xml.println("  </properties>");
  }
  
  private void replace(String k, String v, Xml xml) {
    if ("$encoding".equals(k)) {
      xml.printKvTag(sp4, "project.build.sourceEncoding", v);
      xml.printKvTag(sp4, "project.reporting.outputEncoding", v);
    }
    else if ("$compiler".equals(k)) {
      xml.printKvTag(sp4, "maven.compiler.source", v);
      xml.printKvTag(sp4, "maven.compiler.target", v);
    }
  }
}
