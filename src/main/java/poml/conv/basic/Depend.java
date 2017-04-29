package poml.conv.basic;

import poml.conv.Converter;
import poml.io.Poml;
import poml.io.Xml;
import poml.util.Throw;

public class Depend implements Converter {
  @Override public String name() { return "depend"; }

  @Override public void convert(Poml in, Xml out) {
    for (String lib: in.conf.vals(name())) {
      String[] vals = lib.split(":");
      if (vals.length < 2) {
        Throw.badConf(name(), lib);
      }
      out.line("    <dependency>");
      out.tags(sp6, keys, vals);
      out.line("    </dependency>");
    }
  }
  private static final String[] keys = {
    "groupId", "artifactId", // required
    "version", "scope", "optional", "type"  // optional
  };
}
