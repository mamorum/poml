package poml.conv.basic;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;

public class Pkg implements Converter {

  @Override public String name() { return "pkg"; }

  @Override public void convert(Poml poml, Xml xml) {
    String[] vals = poml.conf.val(name()).trim().split(":");
    // TODO check vals (null, length)
    for (int i = 0; i < vals.length; i++) {
      xml.printKvTag(sp2, tags[i], vals[i]);
    }
  }

  private static final String[] tags = {
    "groupId", "artifactId", "version", "packaging"
  };
}
