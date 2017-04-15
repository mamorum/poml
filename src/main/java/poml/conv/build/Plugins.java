package poml.conv.build;

import poml.conv.Converter;
import poml.conv.build.plugin.Dsg;
import poml.conv.build.plugin.Fatjar;
import poml.io.Poml;
import poml.io.Xml;

public class Plugins  implements Converter {
  @Override public String name() { return "build.plugins"; }
  
  @Override public void convert(Poml poml, Xml xml) {
    String[] plgs = poml.conf.vals(name());
    for (String plg: plgs) {
      if ("&fatjar".equals(plg)) (new Fatjar()).convert(poml, xml);
      else if ("&dsg".equals(plg)) (new Dsg()).add(xml);
      else convert(plg, poml, xml);
    }    
  }

  // Convert user plugin (ex. $comipler)
  private void convert(String plg, Poml poml, Xml xml) {
    String val = poml.conf.val(plg);
    String[] vals = val.split(":");
    xml.out.add("      <plugin>").nl();
    xml.outTags(sp8, tags, vals);  // groupId - inherited
    convert(plg, ".conf", "configuration", poml, xml);
    convert(plg, ".depend", "dependencies", poml, xml);
    convert(plg, ".exec", "executions", poml, xml);
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
