package poml.io;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import poml.Throw;

public class Config {
  Map<String, String> p = new HashMap<>();

  // -> For checking config key.
  public boolean has(String key) {
    return p.containsKey(key);
  }

  // -> For getting config values.
  // key=val
  //  - throw: if val is null or blank
  //  - return: not null or blank
  public String val(String key) {
    return val(key, true);
  }
  private String val(String key, boolean ltrim) {
    String val = p.get(key);
    if (none(val)) Throw.noConf(key);
    if (ltrim) return ltrim(val);
    else return val;
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
      if (none(vals[i])) Throw.badConf(key, val);
      if(hasEsc) vals[i] = vals[i].replace(esc, ",");
      vals[i] = ltrim(vals[i]);
    }
    return vals;
  }
  // key=k>v, k>v, ...
  //  - throw: if map element is null or blank
  //  - return: map (size 1+)
  public Map<String, String> map(String key) {
    String val = val(key);
    Map<String, String> map = new LinkedHashMap<>();
    for (String kv: split(key, val)) {
      if (!put(kv, map)) Throw.badConf(key, val);
    }
    return map;
  }
  //// "k>v" -> put("k", "v")
  ////" k > v " -> put("k", "v ")
  private static boolean put(
    String kv, Map<String, String> map
  ) {
    int pos = kv.indexOf('>');
    if (pos == -1) return false;
    String k = kv.substring(0, pos);
    String v = kv.substring(pos + 1);
    if (none(k)) return false;
    if (none(v)) return false;
    map.put(k.trim(), ltrim(v));
    return true;
  }
  // -> for getting config xml from "{ <k>v</k>  ... }"
  public String xml(String key) {
    return val(key, false);
  }
  private static boolean none(String s) {
    if (s == null || "".equals(s)) return true;
    return false;
  }
  private static String ltrim(String s) {
    int len=s.length(), i=0;
    char[] c = s.toCharArray();
    while ((i < len) && (c[i] <= ' ')) i++;
    return (i > 0 ? s.substring(i) : s);
  }
}
