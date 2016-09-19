package poml.converter.project;

import poml.Converter;
import poml.Dst;
import poml.Src;

public class Model4 {
  
  public static class Start implements Converter {
    public String key() { return "#model4"; }
    private static final String model4 =  // for maven2.
      "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" " +
        "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
        "xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 " +
        "http://maven.apache.org/xsd/maven-4.0.0.xsd\">" +
        System.lineSeparator() +
        "  <modelVersion>4.0.0</modelVersion>";
    @Override public void convert(Src src, Dst dst) {
      dst.out.println(model4); 
    }
  }
  
  public static class End implements Converter {
    public String key() { return "/model4"; }
    private static final String tag = "</project>";
    @Override public void convert(Src src, Dst dst) {
      dst.out.println(tag);
    }
  }
}
