package poml.conv.basic;

import poml.conv.Converter;
import poml.io.Poml;
import poml.io.Xml;
import poml.util.Throw;

public class Depend implements Converter {
  @Override public String name() { return "depend"; }

  @Override public void convert(Poml poml, Xml xml) {
    for (String dep: poml.conf.vals(name())) {
      String[] vals = dep.split(":");
      if (vals.length < 2) {
        Throw.badConf(name(), dep);
      }
      xml.out.add("    <dependency>").nl();
      xml.outTags(sp6, tags, vals);
      xml.out.add("    </dependency>").nl();
    }
  }
  private static final String[] tags = {
    "groupId", "artifactId", // required
    "version", "scope", "optional", "type"  // optional
  };
}
