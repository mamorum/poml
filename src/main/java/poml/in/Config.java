package poml.in;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class Config {

  private Properties p = new Properties();
  private StringBuilder lines = new StringBuilder();
  
  // -> for loading.
  public void append(String line) {
    if ("".equals(line)) return;
    lines.append(line);
    if (isContinue(line)) return;
    lines.append(System.lineSeparator());
  }
  private boolean isContinue(String line) {
    char last = line.charAt(line.length()-1);
    if (last == '=') return true;
    if (last == ',') return true;
    if (last == '{') return true;
    if (last == '>') {
      lines.append("&&");
      return true;
    }
    return false;
  }
  public void load() {
    try (StringReader r = strdr()) {
      p.load(r);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  private StringReader strdr() {
    return new StringReader(
      lines.toString()
    );
  }

  // -> For checking key.
  public boolean has(String key) {
    return p.containsKey(key);
  }
  
  // -> For getting config values.
  // -> Do not implement validation in this class.
  // ? key=_none
  private boolean defaults(String key) {
    return "_default".equals(val(key));
  }
  // key=val
  public String val(String key) {
    return p.getProperty(key);
  }
  // key=val, val, ... (not spilted by escaped "\\,")
  public String[] vals(String key) {
    String val = val(key);
    if (val == null) return null;
    return split(val, "(?<!\\\\),");
  }
  // key=k:v, k:v, ...
  public Map<String, String> map(String key) {
    if (defaults(key)) return new HashMap<>();
    String[] kvs = vals(key);
    if (kvs == null) return new HashMap<>();
    Map<String, String> map = new LinkedHashMap<>();
    for (String kv: kvs) {
      map.put(k(kv),v(kv));
    }
    return map;
  }

  // -> for getting config tags from "{ <k>v</k>  ... }"
  public String tag(String key, String space) {
    String val = val(key);
    if (val == null) return null;
    String tags = val.substring(
      val.indexOf('{') + 1,
      val.lastIndexOf('}')
    ); 
    StringBuilder sb = new StringBuilder();
    for (String tag: tags.split("&&")) {
      sb.append(space);
      sb.append(tag);
      sb.append(System.lineSeparator());
    }
    return sb.toString();
  }
  
  // -> utils
  // split with delim, converting "\\," to ","
  private static final String escma = "\\,";
  private String[] split(String val, String delim) {
    if (!val.contains(escma)) {
      return val.split(delim);
    }
    String[] tmp = val.split(delim);
    String[] vals = new String[tmp.length];
    for (int i=0; i<tmp.length; i++) {
      vals[i] = tmp[i].replace(
          escma, ","
      );
    }
    return vals;
  }
  // get key from key:val
  private static String k(String kv) {
    return kv.substring(
      0, kv.indexOf(':')
    ).trim();
  }
  // get val from key:val
  private static String v(String kv) {
    return kv.substring(
      kv.indexOf(':') + 1
    ).trim();
  }
}
