package poml.conv.build;

import poml.conv.Converter;
import poml.conv.build.plugin.Ossrh;
import poml.conv.build.plugin.Fatjar;
import poml.io.Poml;
import poml.io.Xml;

public class Plugin  implements Converter {
  @Override public String name() { return "plugin"; }

  @Override public void convert(Poml poml, Xml xml) {
    String[] plgs = poml.conf.vals(name());
    for (String plg: plgs) {
      if ("&fatjar".equals(plg)) (new Fatjar()).convert(poml, xml);
      else if ("&ossrh".equals(plg)) (new Ossrh()).add(xml);
      else convert(plg, poml, xml);
    }
  }

  // Convert user plugin (ex. $compiler)
  private void convert(String plg, Poml poml, Xml xml) {
    String val = poml.conf.val(plg);
    String[] vals = val.split(":");
    xml.out.add("      <plugin>").nl();
    xml.outTags(sp8, tags, vals);  // groupId - inherited
    convert(plg, ".conf", "configuration", poml, xml);
    convert(plg, ".depends", "dependencies", poml, xml);
    convert(plg, ".execs", "executions", poml, xml);
    xml.out.add("      </plugin>").nl();
  }
  private void convert(
    String keyPrefix, String keySuffix, String tag, Poml poml, Xml xml
  ) {
    StringBuilder sb = new StringBuilder();
    String key = sb.append(keyPrefix).append(keySuffix).toString();
    if (poml.conf.hasTag(key)) {
      xml.out.add(sp8).add("<").add(tag).add(">").nl();
      xml.out.add(poml.conf.tag(key, sp8));
      xml.out.add(sp8).add("</").add(tag).add(">").nl();
    }
  }
  private static final String[] tags = {
    "groupId", "artifactId", "version", "extensions", "inherited"
  };
}
