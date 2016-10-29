package poml.conv.project;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;

public class Model4 {

  public static class Start implements Converter {
    @Override public String name() { return "#model4"; }
    @Override public void convert(Poml poml, Xml xml) {
      xml.out.println(
        "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" " +
        "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
        "xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 " +
        "http://maven.apache.org/xsd/maven-4.0.0.xsd\">"); 
      xml.out.println("  <modelVersion>4.0.0</modelVersion>");
    }
  }
  public static class End implements Converter {
    @Override public String name() { return "/model4"; }
    @Override public void convert(Poml poml, Xml xml) {
      xml.out.print("</project>");
    }
  }
}
