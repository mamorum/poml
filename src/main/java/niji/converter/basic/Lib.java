package niji.converter.basic;

import java.util.function.Function;

public class Lib implements Function<String, String>{

  static String[] tmpls = {
      "<groupId>0</groupId>", 
      "<artifactId>1</artifactId>",
      "<version>2</version>",
      "<scope>3</scope>",
      "<optional>4</optional>",
      "<type>5</type>"
  };     

  public static String dependency(String v) {
    String[] vals = v.split(":");
    StringBuilder sb = new StringBuilder();
    sb.append("<dependency>").append(System.lineSeparator());
    for (int i = 0; i < vals.length; i++) {
      sb.append(
        tmpls[i].replace(
            Integer.toString(i), vals[i].trim()
        )
      );
      sb.append(System.lineSeparator());
    }
    sb.append("</dependency>").append(System.lineSeparator());
    return sb.toString();
  }

  @Override public String apply(String v) {
    StringBuilder xml = new StringBuilder();
    String [] deps = v.split(",");
    xml.append("<dependencies>").append(System.lineSeparator());
    for (String dep: deps) {
      xml.append(
        dependency(dep.trim())
      );
    }
    xml.append("</dependencies>").append(System.lineSeparator());
    return xml.toString();
  }

}
