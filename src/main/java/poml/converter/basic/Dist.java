package poml.converter.basic;

import java.util.LinkedHashMap;
import java.util.Map;

import poml.Converter;
import poml.Pom;
import poml.Poml;

public class Dist implements Converter {

  @Override public String name() { return "dist"; }

  private static final String[] tags = {
    "groupId", "artifactId", "version", "packaging"
  };

  @Override public void convert(Poml poml, Pom pom) {
    String[] vals = poml.conf.vals(name(), ":");
    Map<String, String> kv = new LinkedHashMap<>();
    // TODO check vals (null, length)
    for (int i = 0; i < vals.length; i++) {
      kv.put(tags[i], vals[i]);
    }
    pom.printKvTags(sp2, kv);
  }
}
