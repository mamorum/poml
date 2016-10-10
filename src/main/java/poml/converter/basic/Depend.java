package poml.converter.basic;

import java.util.LinkedHashMap;
import java.util.Map;

import poml.Converter;
import poml.Pom;
import poml.Poml;

public class Depend implements Converter {

  @Override public String name() { return "depend"; }

  @Override public void convert(Poml poml, Pom pom) {
    for (String lib: poml.conf.vals(name(), ",")) {
      printDependncy(lib.trim(), pom);
    }
  }

  private void printDependncy(String lib, Pom pom) {
    pom.out.println(sp4 + "<dependency>");
    Map<String, String> kv = new LinkedHashMap<>();
    String[] vals = lib.split(":");
    // TODO check vals (null, length)
    for (int i = 0; i < vals.length; i++) {
      kv.put(tags[i], vals[i]);
    }
    pom.printKvTags(sp6, kv);
    pom.out.println(sp4 + "</dependency>");
  }  

  private static final String[] tags = {
    "groupId", "artifactId", "version",
    "scope", "optional", "type"
  };
}
