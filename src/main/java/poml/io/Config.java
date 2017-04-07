package poml.io;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import poml.tool.Throw;

public class Config {

  Properties p = new Properties();
  private StringBuilder ps = new StringBuilder();
  
  // -> for loading.
  public void append(String line) {
    if ("".equals(line)) return;
    ps.append(line);
    if (isContinue(line)) return;
    ps.append(System.lineSeparator());
  }
  private boolean isContinue(String line) {
    char last = line.charAt(line.length()-1);
    if (last == '=') return true;
    if (last == ',') return true;
    if (last == '{') return true;
    if (last == '>') {
      ps.append("&&");
      return true;
    }
    return false;
  }
  public void load() {
    try (
      StringReader r =
        new StringReader(ps.toString())
    ) { p.load(r); }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  // -> For checking key.
  public boolean has(String key) {
    return p.containsKey(key);
  }

  // -> For getting config values.
  // key=val
  //  - throw: if val is null or blank
  //  - return: not null or blank
  public String val(String key) {
    String pv = p.getProperty(key);
    if (pv == null) Throw.noConf(key);
    String val = pv.trim();
    if ("".equals(val)) Throw.badConf(key, pv);
    return val;
  }
  // key=val, val, ... 
  //  - throw: if array element is null or blank
  //  - return: array (size 1+, element length 1+)
  public String[] vals(String key) {
    return split(key, val(key));
  }
  //// split val with "," -> replace "\\," to ","
  private static final String esc = "\\,";
  private String[] split(String key, String val) {
    boolean hasEsc = val.contains(esc);
    String delim = hasEsc ? "(?<!\\\\)," : ",";
    String[] vals = val.split(delim);
    for (int i=0; i<vals.length; i++) {
      vals[i] = vals[i].trim();
      if(hasEsc) vals[i] = vals[i].replace(esc, ",");
      if (none(vals[i])) Throw.badConf(key, val);
    }
    return vals;
  }
  // key=k:v, k:v, ...
  //  - throw: if map element is null or blank
  //  - return: map (size 0), if default
  //  - return: map (size 1+), if not default
  public Map<String, String> map(
    String key, boolean defaultOk
  ) {
    String val = val(key);
    if (defaultOk &&  "_default".equals(val)) {
      return new HashMap<>();
    }
    Map<String, String> map = new LinkedHashMap<>();
    for (String kv: split(key, val)) {
      if (!put(kv, map)) Throw.badConf(key, val);
    }
    return map;
  }
  //// "k:v" -> put("k", "v")
  private static boolean put(
    String kv, Map<String, String> map
  ) {
    int pos = kv.indexOf(':');
    if (pos == -1) return false;
    String k = kv.substring(0, pos).trim();
    String v = kv.substring(pos + 1).trim();
    if (none(k)) return false;
    if (none(v)) return false;
    map.put(k, v);
    return true;
  }
  private static boolean none(String s) {
    if (s == null || "".equals(s)) return true;
    return false;
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
}
