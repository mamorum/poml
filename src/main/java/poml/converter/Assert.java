package poml.converter;

public class Assert {
  public static void exist(String v, String errMsg) {
    if (v == null || v.length() == 0)
      throw new RuntimeException(errMsg); 
  }
}
