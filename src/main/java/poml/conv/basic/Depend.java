package poml.conv.basic;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;
import poml.tool.Func.Assert;

public class Depend implements Converter {

  @Override public String name() { return "depend"; }

  @Override public void convert(Poml poml, Xml xml) {
    converts(name(), poml, xml);
  }

  public void converts(String cname, Poml poml, Xml xml) {
    for (String dep: poml.conf.vals(cname)) {
      xml.out.println("    <dependency>");
      String[] vals = dep.split(":");
      Assert.pkg(vals, cname, dep);
      xml.printKvTags(sp6, tags, vals);
      xml.out.println("    </dependency>");
    }
  }
  private static final String[] tags = {
    "groupId", "artifactId", "version",  // required
    "scope", "optional", "type"  // optional
  };
}
