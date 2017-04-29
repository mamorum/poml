package poml.conv.basic;

import poml.conv.Converter;
import poml.io.Poml;
import poml.io.Xml;
import poml.util.Throw;

public class Pkg implements Converter {
  @Override public String name() { return "pkg"; }

  @Override public void convert(Poml in, Xml out) {
    String val = in.conf.val(name());
    String[] vals = val.split(":");
    if (vals.length < 3) {
      Throw.badConf(name(), val);
    }
    out.tags(sp2, keys, vals);
  }
  private static final String[] keys = {
    "groupId", "artifactId", "version",  // required
    "packaging"  // optional
  };
}
