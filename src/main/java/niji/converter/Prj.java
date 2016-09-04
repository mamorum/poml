package niji.converter;

import niji.Dst;
import niji.Src;
import niji.Converters.Converter;

public class Prj {
  
  public static class Start implements Converter {
    public String key() {return "#prj";}
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
    public String key() {return "/prj";}
    final String tag = "</project>";
    @Override public void convert(Src src, Dst dst) {
      dst.out.println(tag);
    }
  }
}
