package poml.io;

import java.util.HashMap;

import poml.Throw;

public class Config {
  HashMap<String, String> p = new HashMap<>();

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
