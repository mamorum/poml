package poml.converter.env;

import java.util.HashMap;
import java.util.Map;

import poml.Converter;
import poml.in.Poml;
import poml.out.Xml;
import poml.tool.converter.Tmpl;

public class Dist implements Converter {
    
  @Override public String name() { return "dist"; }
    
  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> map = poml.conf.map(name());
    if (map.size() == 0) return;
    
    xml.out.println(sp2 + "<distributionManagement>");
    for (String k: map.keySet()) {
      String v = map.get(k);
      if (!"ossrh".equals(v)) continue;
      String tmpl = ossrh.get(k);
      if (tmpl == null) continue;
      xml.out.print(Tmpl.text(tmpl));
    }
    xml.out.println(sp2 + "</distributionManagement>");
  }
  
  private static Map<String, String> ossrh = new HashMap<>();  
  static {
    ossrh.put("snap", "/converter/env/dist/ossrh/snap.txt");
    ossrh.put("repo", "/converter/env/dist/ossrh/repo.txt");
  }
}
