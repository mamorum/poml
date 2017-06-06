package poml.io;

import java.util.HashMap;

// ConfigSection. keys and vals.
public class Conf {
  HashMap<String, String> kv = new HashMap<>();

  //-> to check key.
  public boolean has(String key) {
    return kv.containsKey(key);
  }

  //-> to get val.
  // key=val
  //// - throw: if val is null or blank
  //// - return: not null or blank
  public String val(String key) {
    String val = kv.get(key);
    if (none(val)) err(key, val);
    return val;
  }
  // key=v:v:v... (colon-separated v)
  //// - throw: same as #val(String)
  //// - return: not null (length 1+)
  public String[] clnsv(String key) {
    String[] vals = val(key).split(":");
    vals[0] = ltrim(vals[0]);
    return vals;
  }
  // key=v, v, ... (comma-separated v)
  ////  - throw:
  ////    - same as #val(String)
  ////    - if array element is null or blank
  ////  - return:
  ////    - not null (length 1+)
  ////    - v replaced "\," to ","
  private static final String esc="\\,", cn=",";
  public String[] csv(String key) {
    String val = val(key);
    boolean hasEsc = (val.indexOf(esc) > -1);
    String delim = hasEsc ? "(?<!\\\\)," : cn;
    String[] vals = val.split(delim);
    for (int i=0; i<vals.length; i++) {
      vals[i] = ltrim(vals[i]);
      if (none(vals[i])) err(key, val);
      if(hasEsc) vals[i] = vals[i].replace(esc, cn);
    }
    return vals;
  }
  private static boolean none(String s) {
    if (s == null || "".equals(s)) return true;
    return false;
  }

  //-> util
  public static String ltrim(String s) {
    int len=s.length(), i=0;
    char[] c = s.toCharArray();
    while ((i < len) && (c[i] <= ' ')) i++;
    return (i > 0 ? s.substring(i) : s);
  }
  public static void err(String k, String v) {
    throw new IllegalStateException(
      "Invalid config [key=" + k +"] [val=" + v + "]"
    );
  }
}
