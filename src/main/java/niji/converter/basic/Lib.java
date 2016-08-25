package niji.converter.basic;

import java.util.function.Function;

import niji.converter.util.Tag;

public class Lib implements Function<String, String>{

  static String[] tmpls = {
      "<groupId>0</groupId>", 
      "<artifactId>1</artifactId>",
      "<version>2</version>",
      "<scope>3</scope>",
      "<optional>4</optional>",
      "<type>5</type>"
  };     

  public static void addChild(String lib, Tag parent) {
    Tag child =Tag.init("<dependency>", "</dependency>");
    String[] childVals = lib.split(":");
    for (int i = 0; i < childVals.length; i++) {
      child.line(
        tmpls[i].replace(
            Integer.toString(i),
            childVals[i].trim()
        )
      );
    }
    parent.child(child);
  }

  @Override public String apply(String v) {
    Tag parent = Tag.init(
      "<dependencies>", "</dependencies>"
    );
    for (String lib: v.split(",")) {
      addChild(lib.trim(), parent);
    }
    return parent.string();
  }
}
