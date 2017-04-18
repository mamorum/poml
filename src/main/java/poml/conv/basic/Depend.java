package poml.conv.basic;

import poml.conv.Converter;
import poml.conv.Is;
import poml.io.Poml;
import poml.io.Xml;
import poml.tool.Throw;

public class Depend implements Converter {

  @Override public String name() { return "depend"; }

  @Override public void convert(Poml poml, Xml xml) {
    for (String dep: poml.conf.vals(name())) {
      xml.out.add("    <dependency>").nl();
      String[] vals = dep.split(":");
      if (!Is.lib(vals)) Throw.badConf(name(), dep);
      xml.outTags(sp6, tags, vals);
      xml.out.add("    </dependency>").nl();
    }
  }
  private static final String[] tags = {
    "groupId", "artifactId", "version",  // required
    "scope", "optional", "type"  // optional
  };
}
