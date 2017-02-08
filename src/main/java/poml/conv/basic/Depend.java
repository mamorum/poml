package poml.conv.basic;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;
import poml.tool.Is;
import poml.tool.Throw;

public class Depend implements Converter {

  @Override public String name() { return "depend"; }

  @Override public void convert(Poml poml, Xml xml) {
    converts(name(), poml, xml);
  }

  public void converts(String cname, Poml poml, Xml xml) {
    for (String dep: poml.conf.vals(cname)) {
      xml.println("    <dependency>");
      String[] vals = dep.split(":");
      if (!Is.lib(vals)) Throw.badConf(name(), dep);
      xml.printKvTags(sp6, tags, vals);
      xml.println("    </dependency>");
    }
  }
  private static final String[] tags = {
    "groupId", "artifactId", "version",  // required
    "scope", "optional", "type"  // optional
  };
}
