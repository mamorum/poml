package poml.converter;

import poml.Dst;
import poml.Src;
import poml.Converters.Converter;

public class Model4 {
  
  public static class Start implements Converter {
    public String key() {return "#model4";}
    final String model4 =  // for maven2.
      "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" " +
        "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
        "xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 " +
        "http://maven.apache.org/xsd/maven-4.0.0.xsd\">" +
        System.lineSeparator() +
        "  <modelVersion>4.0.0</modelVersion>" +
        System.lineSeparator();
    @Override public void convert(Src src, Dst dst) {
      dst.out.println(model4); 
    }
  }
  
  public static class End implements Converter {
    public String key() {return "/model4";}
    final String tag = "</project>";
    @Override public void convert(Src src, Dst dst) {
      dst.out.println(tag);
    }
  }
}
