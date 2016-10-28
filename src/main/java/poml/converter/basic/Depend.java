package poml.converter.basic;

import poml.Converter;
import poml.in.Poml;
import poml.out.Xml;

public class Depend implements Converter {

  @Override public String name() { return "depend"; }

  @Override public void convert(Poml poml, Xml xml) {
    print(name(), poml, xml);
  }

  public void print(String cname, Poml poml, Xml xml) {
    for (String lib: poml.conf.vals(cname)) {
      xml.out.println("    <dependency>");
      printLib(lib, xml);
      xml.out.println("    </dependency>");
    }
  }

  private void printLib(String lib, Xml xml) {
    // TODO check vals (null, length)
    String[] vals = lib.trim().split(":");
    for (int i = 0; i < vals.length; i++) {
      xml.printKvTag(sp6, tags[i], vals[i]);
    }
  }

  private static final String[] tags = {
    "groupId", "artifactId", "version",
    "scope", "optional", "type"
  };
}
