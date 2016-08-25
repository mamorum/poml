package niji.converter;

import java.util.function.Function;

public class Prj {

  public static class Start implements Function<String, String> {
  
    final String model4 = 
      "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" " +
        "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
        "xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 " +
        "http://maven.apache.org/xsd/maven-4.0.0.xsd\">" +
        System.lineSeparator() +
        "  <modelVersion>4.0.0</modelVersion>" +
        System.lineSeparator();
    
    @Override public String apply(String v) {
      return model4; // now always maven 2.
    }
  }
  
  public static class End implements Function<String, String> {
    @Override public String apply(String v) {
      return "</project>";
    }
  }
}
