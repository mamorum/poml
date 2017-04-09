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
}
