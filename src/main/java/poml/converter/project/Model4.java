package poml.converter.project;

import poml.Converter;
import poml.Dst;
import poml.Src;

public class Model4 {

  public static class Start extends Converter {
    @Override public String name() { return "#model4"; }
    @Override public void convert(Src src, Dst dst) {
      dst.out.println(
        "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" " +
        "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
        "xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 " +
        "http://maven.apache.org/xsd/maven-4.0.0.xsd\">"); 
      dst.out.println(sp2 + "<modelVersion>4.0.0</modelVersion>");
    }
  }
  public static class End extends Converter {
    @Override public String name() { return "/model4"; }
    @Override public void convert(Src src, Dst dst) {
      dst.out.println("</project>");
    }
  }
}
