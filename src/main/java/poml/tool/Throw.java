package poml.tool;

public class Throw {
  public static void noConfig(String key) {
    throw new IllegalStateException(
      "No config found [key=" + key + "]."
    );
  }
  public static void badConfig(String key, String val, String msg) {
    throw new IllegalStateException(
      "Bad config [key=" + key +", val=" + val + "]. " + msg
    );
  }
}
