package poml.converter.basic;

import java.util.LinkedHashMap;
import java.util.Map;

import poml.Converter;
import poml.Dst;
import poml.Src;

public class Lib implements Converter {

  @Override public String name() { return "lib"; }
  
  private static final String[] tags = {
    "groupId", "artifactId", "version",
    "scope", "optional", "type"
  };

  @Override public void convert(Src src, Dst dst) {
    dst.out.println(sp2 + "<dependencies>");
    for (String lib: src.propList(name(), ",")) {
      printDependncy(lib.trim(), dst);
    }
    dst.out.println(sp2 + "</dependencies>");
  }

  public void printDependncy(String lib, Dst dst) {
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
}
