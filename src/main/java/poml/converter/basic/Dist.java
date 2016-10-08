package poml.converter.basic;

import java.util.LinkedHashMap;
import java.util.Map;

import poml.Converter;
import poml.Dst;
import poml.Src;

public class Dist implements Converter {

  @Override public String name() { return "dist"; }

  private static final String[] tags = {
    "groupId", "artifactId", "version", "packaging"
  };

  @Override public void convert(Src src, Dst dst) {
    String[] vals = src.conf.vals(name(), ":");
    Map<String, String> kv = new LinkedHashMap<>();
    // TODO check vals (null, length)
    for (int i = 0; i < vals.length; i++) {
      kv.put(tags[i], vals[i]);
    }
    dst.printKvTags(sp2, kv);
  }
}
