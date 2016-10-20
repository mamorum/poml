package poml.in;

import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class Config {

  public Properties p = new Properties();
  public StringBuilder lines = new StringBuilder();
  
  // -> for loading.
  public void append(String line) {
    if (line.endsWith(";")) {
      terminate(line);
      return;
    }
    lines.append(line);
    if (line.endsWith(">")) lines.append(",");
  }
  private void terminate(String line) {
    lines.append(
      line.substring(0, line.length()-1)
    );
    lines.append(System.lineSeparator());
  }
  
  public void load() throws IOException {
    try (
      StringReader r
        = new StringReader(lines.toString())
    ) { p.load(r); }
  }

  // -> for getting config values.
  private static final String cma = "&comma;";
  private String[] split(String val, String delim) {
    if (!val.contains(cma)) {
      return val.split(delim);
    }
    String[] tmp = val.split(delim);
    String[] vals = new String[tmp.length];
    for (int i=0; i<tmp.length; i++) {
      vals[i] = tmp[i].replace(
          cma, ","
      );
    }
    return vals;
  }

  private static final String[] zeros = {};
  public String[] tags(String key) {
    String val = p.getProperty(key);
    if (val == null) return zeros;
    String tag = val.substring(
      val.indexOf('{') + 1,
      val.lastIndexOf('}')
    ); 
    return split(tag, ",");
  }
  
  // key=val, val, ... ( if delim is "," )
  public String[] vals(String key, String delim) {
    String val = p.getProperty(key);
    if (val == null) return null;
    return split(val, delim);
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
