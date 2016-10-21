package poml.converter.basic;

import java.util.LinkedHashMap;
import java.util.Map;

import poml.Converter;
import poml.in.Poml;
import poml.out.Xml;

public class Depend implements Converter {

  @Override public String name() { return "depend"; }

  @Override public void convert(Poml poml, Xml xml) {
    for (String lib: poml.conf.vals(name())) {
      printDependncy(lib.trim(), xml);
    }
  }

  private void printDependncy(String lib, Xml xml) {
    xml.out.println(sp4 + "<dependency>");
    Map<String, String> kv = new LinkedHashMap<>();
    String[] vals = lib.split(":");
    // TODO check vals (null, length)
    for (int i = 0; i < vals.length; i++) {
      kv.put(tags[i], vals[i]);
    }
    xml.printKvTags(sp6, kv);
    xml.out.println(sp4 + "</dependency>");
  }  

  private static final String[] tags = {
    "groupId", "artifactId", "version",
    "scope", "optional", "type"
  };
}
