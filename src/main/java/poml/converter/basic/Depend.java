package poml.converter.basic;

import java.util.LinkedHashMap;
import java.util.Map;

import poml.Converter;
import poml.Dst;
import poml.Src;

public class Depend implements Converter {

  @Override public String name() { return "depend"; }

  @Override public void convert(Src src, Dst dst) {
    for (String lib: src.conf.vals(name(), ",")) {
      printDependncy(lib.trim(), dst);
    }
  }

  private void printDependncy(String lib, Dst dst) {
    dst.out.println(sp4 + "<dependency>");
    Map<String, String> kv = new LinkedHashMap<>();
    String[] vals = lib.split(":");
    // TODO check vals (null, length)
    for (int i = 0; i < vals.length; i++) {
      kv.put(tags[i], vals[i]);
    }
    dst.printKvTags(sp6, kv);
    dst.out.println(sp4 + "</dependency>");
  }  

  private static final String[] tags = {
    "groupId", "artifactId", "version",
    "scope", "optional", "type"
  };
}
