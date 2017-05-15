package poml.conv.basic;

import poml.Throw;
import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;

public class Pkg implements Converter {
  public static final String name = "pkg";
  @Override public String name() { return name; }

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
