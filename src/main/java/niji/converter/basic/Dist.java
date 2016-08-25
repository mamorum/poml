package niji.converter.basic;

import java.util.function.Function;

import niji.converter.util.Tag;

public class Dist implements Function<String, String> {

  static String[] tmpls = {
      "<groupId>0</groupId>", 
      "<artifactId>1</artifactId>",
      "<version>2</version>",
      "<packaging>3</packaging>"
  };     

  public static String tags(String v) {
    String[] vals = v.split(":");
    Tag tag = Tag.init();
    for (int i = 0; i < vals.length; i++) {
      tag.line(
        tmpls[i].replace(
            Integer.toString(i), vals[i].trim()
        )
      );
    }
    return tag.string();
  }

  @Override public String apply(String v) {
    return tags(v);
  }
}
