package poml.conv.env;

import java.util.Map;

import poml.conv.Converter;
import poml.io.Poml;
import poml.io.Xml;

public class Issue implements Converter {

  @Override public String name() { return "issue"; }

  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> map = poml.conf.map(name(), false);
    xml.out.add("  <issueManagement>").nl();  // -> elements are not required 
    xml.outTag(sp4, sys, map.get(sys));
    xml.outTag(sp4, url, map.get(url));
    xml.out.add("  </issueManagement>").nl();
  }
  private static final String sys = "system", url = "url";
}
