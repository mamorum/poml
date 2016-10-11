package poml.converter.more;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import poml.Converter;
import poml.Xml;
import poml.tool.converter.Tmpl;
import poml.Poml;

// More Project Infomation
//   -> name, desc, url, inceptYear, license
public class Info implements Converter {
    
  @Override public String name() { return "info"; }
    
  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> map = poml.conf.map(name());
    if (map.size() == 0) return;
    
    // name, description, url, inceptionYear
    Map<String, String> kv = new LinkedHashMap<>();
    for (int i = 0; i < flatKeys.length; i++) {
      String key = flatKeys[i];
      String value = map.get(key);
      if (value == null) continue;
      kv.put(key, value);
    }
    xml.printKvTags(sp2, kv);
    
    // license
    String license = map.get(licenseKey);
    if (license == null) return;
    String licensePath = licenses.get(license.trim());
    if (licensePath == null) throw new IllegalStateException(
      "License \"" + license + "\" not found"
    );
    xml.out.print(Tmpl.text(licensePath));
  }
  
  // --> property keys
  private static final String[] flatKeys =
    {"name", "description", "url", "inceptionYear", };
  private static final String licenseKey = "license";
  
  // --> licenses
  //  http://central.sonatype.org/pages/requirements.html#license-information
  private static Map<String, String> licenses = new HashMap<>();  
  static {
    licenses.put("MIT", "/converter/more/info/mit.txt");
    licenses.put("Apache 2.0", "/converter/more/info/apache2.txt");
  }
}
