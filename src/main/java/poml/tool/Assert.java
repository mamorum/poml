package poml.tool;

import java.util.Map;

public class Assert {
  public static void pkg(
    String[] vals, String key, String val
  ) {
    if (vals.length < 3) Throw.badConfig(
      key, val, "Required [val=groupId:artifactId:version]."
    );
  }
  public static void notNull(
    String k, Map<String, String> in, String cname
  ) {
    if (in.get(k) == null) throw new RuntimeException(
      "\"" + cname + "\" requires \"" + k + "\"" +
      " in property section"
    );
  }
  public static void notNull(
    String[] k, Map<String, String> in, String cname
  ) {
    for (String key: k) notNull(key, in, cname);
  }
}
