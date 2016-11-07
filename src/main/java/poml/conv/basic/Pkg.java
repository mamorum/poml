package poml.conv.basic;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;
import poml.tool.Func.Assert;

public class Pkg implements Converter {

  @Override public String name() { return "pkg"; }

  @Override public void convert(Poml poml, Xml xml) {
    String val = poml.conf.val(name());
    String[] vals = val.split(":");
    Assert.pkg(vals, name(), val);
    xml.printKvTags(sp2, tags, vals);
  }
  private static final String[] tags = {
    "groupId", "artifactId", "version",  // required
    "packaging"  // optional
  };
}
