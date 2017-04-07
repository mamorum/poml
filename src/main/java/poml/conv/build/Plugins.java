package poml.conv.build;

import java.util.LinkedHashMap;
import java.util.Map;

import poml.conv.Converter;
import poml.conv.build.plugin.Fatjar;
import poml.conv.build.plugin.Gpg;
import poml.conv.build.plugin.Javadoc;
import poml.conv.build.plugin.Source;
import poml.io.Poml;
import poml.io.Xml;

public class Plugins  implements Converter {

  public static String name = "build.plugins";
  @Override public String name() { return name; }
  
  private Map<String, Converter> convs = new LinkedHashMap<>();
  private void put(String key, Converter c) { convs.put(key, c); }
  public Plugins(Poml poml) {
    String[] plgs = poml.conf.vals(name);
    for (String p: plgs) {
      if ("&fatjar".equals(p)) put("&fatjar", new Fatjar());
      else if ("&gpg".equals(p)) put("&gpg", new Gpg());
      else if ("&source".equals(p)) put("&source", new Source());
      else if ("&javadoc".equals(p)) put("&javadoc", new Javadoc());
      else convs.put(p, new Plugin(p));  // user plugin
    }
  }
  public Map<String, Converter> export() { return convs; }
  
  @Override public void convert(Poml poml, Xml xml) {
    xml.out.add("    <plugins>").nl();
    for (String plgName: convs.keySet()) {
      convs.get(plgName).convert(poml, xml);
    }
    xml.out.add("    </plugins>").nl();
  }
  
  static class Plugin implements Converter {
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
      if (poml.conf.has(key)) {
        xml.out.add(sp8).add("<").add(tag).add(">").nl();
        xml.out.add(poml.conf.tag(key, sp10));
        xml.out.add(sp8).add("</").add(tag).add(">").nl();
      }
    }
  }
}
