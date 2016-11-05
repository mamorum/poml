package poml.conv.basic;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;
import poml.tool.Throw;

public class Pkg implements Converter {

  @Override public String name() { return "pkg"; }

  @Override public void convert(Poml poml, Xml xml) {
    String val = poml.conf.val(name());
    String[] vals = val.trim().split(":");
    if (vals.length < 3) Throw.badConfig(
      name(), val, "Required [val=groupId:artifactId:version]."
    );
    for (int i = 0; i < vals.length; i++) {
      xml.printKvTag(sp2, tags[i], vals[i]);
    }
  }

  private static final String[] tags = {
    "groupId", "artifactId", "version",  // required
    "packaging"  // optional
  };
}
