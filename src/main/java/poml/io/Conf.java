package poml.io;

import java.util.HashMap;

// config section. keys and vals.
public class Conf {
  HashMap<String, String> kv = new HashMap<>();

  public boolean has(String key) {
    return kv.containsKey(key);
  }
  private String get(String key) {
    String val = kv.get(key);
    if (none(val)) err(key, val);
    return val;
  }

  // "key=  val"
  /// - return: "val" (left trimed)
  /// - throw: if val is null or empty
  public String val(String key) {
    return ltrim(get(key));
  }
  // "key=  v, v, ..." (val is comma-separated-v)
  /// - return: {"v", "v", ...} (left trimed, "\," is replaced to ",")
  /// - throw: if val or v are null or empty
  private static final String esc="\\,", cn=",";
  public String[] csv(String key) {
    String val = get(key);
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
  // "key=  <k>..." (val is xml)
  /// - return: "  <k>..." (not trimed)
  /// - throw: if val is null or empty
  public String xml(String key) {
    return get(key);
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
  private static void err(String k, String v) {
    throw new RuntimeException(
      "Invalid config [key=" + k +"] [val=" + v + "]"
    );
  }
}
