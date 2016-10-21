package poml.converter.basic;

import java.util.LinkedHashMap;
import java.util.Map;

import poml.Converter;
import poml.in.Poml;
import poml.out.Xml;

public class Pkg implements Converter {

  @Override public String name() { return "pkg"; }

  private static final String[] tags = {
    "groupId", "artifactId", "version", "packaging"
  };

  @Override public void convert(Poml poml, Xml xml) {
    String[] vals = poml.conf.val(name()).split(":");
    Map<String, String> kv = new LinkedHashMap<>();
    // TODO check vals (null, length)
    for (int i = 0; i < vals.length; i++) {
      kv.put(tags[i], vals[i]);
    }
    xml.printKvTags(sp2, kv);
  }
}
