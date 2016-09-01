package niji.template.converter.basic;

import niji.template.Converter;
import niji.template.Src;
import niji.util.Str;

public class Lib implements Converter {

  public static String key = "lib";
  
  static String[] tmpls = {
      "<groupId>0</groupId>", 
      "<artifactId>1</artifactId>",
      "<version>2</version>",
      "<scope>3</scope>",
      "<optional>4</optional>",
      "<type>5</type>"
  };

  @Override public void toXml(Src src, StringBuilder xml) {
    Str.ln(src.indent, "<dependencies>", xml);
    for (String lib: src.propertyList(key)) {
      addChild(src.indent+2, lib.trim(), xml);
    }
    Str.ln(src.indent, "</dependencies>", xml);
  }

  public void addChild(
      int indent, String lib, StringBuilder xml
  ) {
    Str.ln(indent, "<dependency>", xml);
    String[] vals = lib.split(":");
    for (int i = 0; i < vals.length; i++) {
      String tag = tag(i, vals[i]);
      Str.ln(indent+2, tag, xml);
    }
    Str.ln(indent, "</dependency>", xml);
  }

  public String tag(int index, String val) {
    return tmpls[index].replace(
        Integer.toString(index),
        val.trim()
    );
  }
}
