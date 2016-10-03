package poml.converter.more;

import java.util.HashMap;
import java.util.Map;

import poml.Converter;
import poml.Dst;
import poml.Src;

public class License implements Converter {
  @Override public String name() { return "license"; }
  @Override public void convert(Src src, Dst dst) {
    String key = src.prop(name());
    String license = licenses.get(key.trim());
    if (license == null) throw new IllegalStateException(
      "License Not found for \"" + key + "\""
    );
    dst.out.println(license);
  }

  // --> license tags from
  //  http://central.sonatype.org/pages/requirements.html#license-information
  private static final String mit = 
      sp2 + "<licenses>" + nl +
        sp4 + "<license>" + nl +
          sp6 + "<name>MIT License</name>" + nl +
          sp6 + "<url>http://www.opensource.org/licenses/mit-license.php</url>" + nl +
        sp4 + "</license>" + nl +
      sp2 + "</licenses>";
  private static String apache2 = 
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
