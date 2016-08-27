package niji.converter.func;

import niji.Converter.Func;
import niji.converter.Src;

public class Prj {
  
  public static class Start implements Func {
    public static final String key = "#prj";
    final String model4 =  // for maven2.
      "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" " +
        "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
        "xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 " +
        "http://maven.apache.org/xsd/maven-4.0.0.xsd\">" +
        System.lineSeparator() +
        "  <modelVersion>4.0.0</modelVersion>" +
        System.lineSeparator();
    @Override public void accept(Src src, StringBuilder xml) {
      xml.append(model4); 
    }
  }
  
  public static class End implements Func {
    public static final String key = "/prj";
    final String tag = "</project>";
    @Override public void accept(Src src, StringBuilder xml) {
      xml.append(tag);
    }
  }
}
