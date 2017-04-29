package poml.conv.basic;

import poml.conv.Converter;
import poml.io.Poml;
import poml.io.Xml;
import poml.util.Throw;

public class Parent implements Converter{
  @Override public String name() { return "parent"; }

  @Override public void convert(Poml in, Xml out) {
    String val = in.conf.val(name());
    String[] vals = val.split(":");
    if (vals.length < 3) {
      Throw.badConf(name(), val);
    }
    out.line("  <parent>");
    out.tags(sp4, keys, vals);
    out.line("  </parent>");
  }
  private static final String[] keys = {
    "groupId", "artifactId", "version",  // required
  };
}

