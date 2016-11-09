package poml.tool;

// TODO Needs message harmony of all methods.
public class Throw {
  public static void noConv(String name) {
    throw new IllegalStateException(
      "Converter not found [name=" + name + "]"
    );
  }
  public static void noConf(String key) {
    throw new IllegalStateException(
      "Config not found [key=" + key + "]"
    );
  }
  public static void badConf(String key, String val) {
    throw new IllegalStateException(
      "Bad config [key=" + key +"] [val=" + val + "]"
    );
  }
  public static void noKv(String conv, String[] ks) {
    StringBuilder vals = new StringBuilder();
    int last = ks.length - 1;
    for (int i=0; i<ks.length; i++) {
      if (i == last) vals.append(ks[i]).append(":v");
      else vals.append(ks[i]).append(":v, ");
    }
    throw new IllegalStateException(
      "Bad config [key=" + conv +"]" +
      " Requires [val=" + vals.toString() + "]"
    );
  }
  public static void noKv(String conv, String k) {
    throw new IllegalStateException(
      "Bad config [key=" + conv +"]" +
      " Requires [val=" + k + ":v]"
    );
  }
}
