package poml.conv.basic;

import poml.conv.Converter;
import poml.io.Poml;
import poml.io.Xml;
import poml.tool.Is;
import poml.tool.Throw;

public class Parent implements Converter{

  @Override public String name() { return "parent"; }

  @Override public void convert(Poml poml, Xml xml) {
    String val = poml.conf.val(name());
    String[] vals = val.split(":");
    if (!Is.pkg(vals)) Throw.badConf(name(), val);
    xml.out.add("  <parent>").nl();
    xml.outTags(sp4, tags, vals);
    xml.out.add("  </parent>").nl();
  }
  private static final String[] tags = {
    "groupId", "artifactId", "version",  // required
  };
}
  
