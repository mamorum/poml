package poml;

import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class Config {

  public Properties p = new Properties();
  public StringBuilder lines = new StringBuilder();
  
  private boolean isContinue(String line) {
    if (line.endsWith("=")) return true;
    if (line.endsWith(":")) return true;
    if (line.endsWith(",")) return true;
    return false;
  }
  
  // -> for loading.
  public void append(String line) {
    lines.append(line);
    if (isContinue(line)) return;
    lines.append(System.lineSeparator());
  }
  public void load() throws IOException {
    try (
      StringReader r
        = new StringReader(lines.toString())
    ) { p.load(r); }
  }
  
  // -> for getting config values.
  // key=val
  public String val(String key) {
    return p.getProperty(key);
  }
  
  // key=val, val, ... ( if delim is "," )
  public String[] vals(String key, String delim) {
    String vals = val(key);
    if (vals == null) return null;
    return vals.split(delim);
  }
  
  // key=k:v, k:v, ...
  public Map<String, String> map(String key) {
    Map<String, String> map = new LinkedHashMap<>();
    String[] kvs = vals(key, ",");
    if (kvs == null) return map;
    for (String kv: kvs) {
      map.put(mapKey(kv), mapVal(kv));
    }
    return map;
  }
  // key from k:v
  private static String mapKey(String kv) {
    return kv.substring(
      0, kv.indexOf(':')
    ).trim();
  }
  // val from k:v
  private static String mapVal(String kv) {
    return kv.substring(
      kv.indexOf(':') + 1
    ).trim();
  }
}
