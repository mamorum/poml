package poml.conv.build;

import java.util.HashMap;
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

  private static String name = "build.plugins";
  @Override public String name() { return name; }

  public static Map<String, Converter> sysPlugins = new HashMap<>();
  public static Converter[] convs = {
    new Fatjar(), new Gpg(), new Javadoc(), new Source()
  };
  static {
    for (Converter c: convs) sysPlugins.put(c.name(), c);
  }
  
  private Map<String, Converter> target = new LinkedHashMap<>(); 
  
  public Map<String, Converter> load(Poml poml) {
    String[] plgNames = poml.conf.vals(name);
    for (String plgName: plgNames) {
      Converter c = sysPlugins.get(plgName);
      if (c == null) target.put(plgName, new Plugin(plgName));
      else target.put(c.name(), c);
    }
    return target;
  }
  
  @Override public void convert(Poml poml, Xml xml) {
    xml.out.add("    <plugins>").nl();
    for (String plgName: target.keySet()) {
      target.get(plgName).convert(poml, xml);
    }
    xml.out.add("    </plugins>").nl();
  }
  
  private static class Plugin implements Converter {
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
