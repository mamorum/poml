package poml.converter.more;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import poml.Converter;
import poml.Xml;
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
    String licenseTag = licenses.get(license.trim());
    if (licenseTag == null) throw new IllegalStateException(
      "License \"" + license + "\" not found"
    );
    xml.out.println(licenseTag);
  }
  
  // --> property keys
  private static final String[] flatKeys =
    {"name", "description", "url", "inceptionYear", };
  private static final String licenseKey = "license";
  
  // --> license tags
  //  http://central.sonatype.org/pages/requirements.html#license-information
  private static final String mit = 
    sp2 + "<licenses>" + nl +
      sp4 + "<license>" + nl +
        sp6 + "<name>MIT License</name>" + nl +
        sp6 + "<url>http://www.opensource.org/licenses/mit-license.php</url>" + nl +
      sp4 + "</license>" + nl +
    sp2 + "</licenses>";
  private static final String apache2 = 
    sp2 + "<licenses>" + nl +
      sp4 + "<license>" + nl +
        sp6 + "<name>The Apache License, Version 2.0</name>" + nl +
        sp6 + "<url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>" + nl +
      sp4 + "</license>" + nl +
    sp2 + "</licenses>";
  private static Map<String, String> licenses = new HashMap<>();  
  static {
    licenses.put("MIT", mit);
    licenses.put("Apache 2.0", apache2);
  }
}
