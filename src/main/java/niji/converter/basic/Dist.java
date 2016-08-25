package niji.converter.basic;

import java.util.function.Function;

public class Dist implements Function<String, String>{

  static String[] tmpls = {
      "<groupId>0</groupId>", 
      "<artifactId>1</artifactId>",
      "<version>2</version>",
      "<packaging>3</packaging>"
  };     

  public static String xml(String v) {
    String[] vals = v.split(":");
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < vals.length; i++) {
      sb.append(
        tmpls[i].replace(
            Integer.toString(i), vals[i].trim()
        )
      );
      sb.append(System.lineSeparator());
    }
    return sb.toString();
  }

  @Override public String apply(String v) {
    return xml(v);
  }
}
