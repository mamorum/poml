package poml.conv.prj;

import poml.conv.Converter;
import poml.io.Poml;
import poml.io.Xml;

public class Model4 {

  public static class Start implements Converter {
    @Override public String name() { return "#model4"; }
    @Override public void convert(Poml poml, Xml xml) {
      xml.out.add(
        "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" " +
        "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
        "xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 " +
        "http://maven.apache.org/xsd/maven-4.0.0.xsd\">").nl(); 
      xml.out.add("  <modelVersion>4.0.0</modelVersion>").nl();
    }
  }
  public static class End implements Converter {
    @Override public String name() { return "/model4"; }
    @Override public void convert(Poml poml, Xml xml) {
      xml.out.add("</project>");
    }
  }
}