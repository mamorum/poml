package poml.conv.basic;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;

public class Depend implements Converter {

  @Override public String name() { return "depend"; }

  @Override public void convert(Poml poml, Xml xml) {
    converts(name(), poml, xml);
  }

  public void converts(String cname, Poml poml, Xml xml) {
    String[] deps = poml.conf.vals(cname);
    if (deps == null) throw new IllegalStateException(
      "Config not found [key=" + cname + "]"
    );
    for (String dep: deps) {
      xml.out.println("    <dependency>");
      printTags(cname, dep, xml);
      xml.out.println("    </dependency>");
    }
  }

  private void printTags(String cname, String dep, Xml xml) {
    String[] vals = dep.trim().split(":");
    if (vals.length < 3) throw new IllegalStateException(
      "Bad config val [key=" + cname +", val=" + dep + "] " +
      "Please set \"groupId:artifactId:version\" at least"
    );
    for (int i = 0; i < vals.length; i++) {
      if ("".equals(vals[i])) continue;
      xml.printKvTag(sp6, tags[i], vals[i]);
    }
  }

  private static final String[] tags = {
    "groupId", "artifactId", "version",  // required
    "scope", "optional", "type"  // optional
  };
}
