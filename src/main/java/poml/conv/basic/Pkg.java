package poml.conv.basic;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;
import poml.tool.Assert;

public class Pkg implements Converter {

  @Override public String name() { return "pkg"; }

  @Override public void convert(Poml poml, Xml xml) {
    String val = poml.conf.val(name());
    String[] vals = val.trim().split(":");
    Assert.pkg(vals, name(), val);
    for (int i = 0; i < vals.length; i++) {
      xml.printKvTag(sp2, tags[i], vals[i]);
    }
  }

  private static final String[] tags = {
    "groupId", "artifactId", "version",  // required
    "packaging"  // optional
  };
}
