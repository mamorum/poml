package poml;

public class Throw {
  public static void val(String key, String val) {
    throw new IllegalStateException(
      "Invalid config val [key=" + key +"] [val=" + val + "]"
      // TODO add? "Requires ..." (pkg->groupId:.., fatjar->mainClass>v)
      // TODO add? "Please check the poml reference"
    );
  }
  public static void noConf(String key) {
    throw new IllegalStateException(
      "Config not found [key=" + key + "]"
    );
  }
}
