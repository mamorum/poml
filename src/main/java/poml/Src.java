package poml;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class Src {

  public BufferedReader in;
  public String line;
  public Properties prop = new Properties();
  
  public static Src open(String path) throws IOException {
    Src s = new Src();
    s.in = new BufferedReader(new InputStreamReader
      (new FileInputStream(path), "UTF-8")
    );
    return s;
  }
  
  public Src loadProperties() throws IOException {
    String line = null;
    StringBuilder txt = new StringBuilder();
    while ((line = in.readLine()) != null) {
      if (line.equals("---")) break;
      txt.append(line);
      txt.append(System.lineSeparator());
    }
    try (
      StringReader r
        = new StringReader(txt.toString())
    ) {
      prop.load(r);
    }
    return this;
  }

  public void close() {
    try {
      if (in != null) in.close();
    } catch (IOException e) {
      // Ignore, because ...
      // - no idea to recover.
      // - xml is generated.
      // - this process ends soon.
    }
  }

  // --> utilities
  public String prop(String key) {
    return prop.getProperty(key);
  }
  public String[] propList(String key, String delim) {
    String pp = prop(key);
    if (pp == null) return null;
    else return pp.split(delim);
  }
  public Map<String, String> propMap(String key) {
    Map<String, String> map = new LinkedHashMap<>();
    String[] propList = propList(key, ",");
    if (propList == null) return map;
    for (String pp: propList) {
      String[] kv = pp.split(":");
      String k = kv[0].trim();
      String v = kv[1].trim();
      map.put(k, v);
    }
    return map;
  }

}
