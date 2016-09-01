package niji.template.converter.basic;

import niji.template.Converter;
import niji.template.Src;
import niji.util.Str;

public class Dist implements Converter {

  public static String key = "dist";

  static String[] tmpls = {
      "<groupId>0</groupId>", 
      "<artifactId>1</artifactId>",
      "<version>2</version>",
      "<packaging>3</packaging>"
  };

  @Override public void toXml(Src src, StringBuilder xml) {
    String[] vals = src.property(key).split(":");
    for (int i = 0; i < vals.length; i++) {
      String tag = tag(i, vals[i]);
      Str.ln(src.indent, tag, xml);
    }
  }
  
  public String tag(int index, String val) {
    return tmpls[index].replace(
        Integer.toString(index),
        val.trim()
    );
  }
}
