package poml.conv.basic;

import poml.conv.Converter;
import poml.io.Poml;
import poml.io.Xml;
import poml.util.Throw;

public class Pkg implements Converter {

  @Override public String name() { return "pkg"; }

  @Override public void convert(Poml poml, Xml xml) {
    String val = poml.conf.val(name());
    String[] vals = val.split(":");
    if (vals.length < 3) {
      Throw.badConf(name(), val);
    }
    xml.outTags(sp2, tags, vals);
  }
  private static final String[] tags = {
    "groupId", "artifactId", "version",  // required
    "packaging"  // optional
  };
}
