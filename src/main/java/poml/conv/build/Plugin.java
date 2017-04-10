package poml.conv.build;

import poml.conv.Converter;
import poml.io.Poml;
import poml.io.Xml;

public class Plugin implements Converter {
  String name;
  public Plugin(String name) { this.name = name; }
  @Override public String name() { return name; }

  private static final String[] tags = {
    "groupId", "artifactId", "version", "extensions", "inherited"
  };
  @Override  public void convert(Poml poml, Xml xml) {
    String val = poml.conf.val(name);
    String[] vals = val.split(":");
    xml.out.add("      <plugin>").nl();
    xml.outTags(sp8, tags, vals);  // groupId - inherited
    convert(name, ".conf", "configuration", poml, xml);
    convert(name, ".depend", "dependencies", poml, xml);
    convert(name, ".depend", "executions", poml, xml);
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

}
