package niji.template.converter.basic;

import niji.template.Converter;
import niji.template.Src;
import niji.template.converter.util.Tag;

public class Dist implements Converter {

  public static String key = "dist";

  static String[] tmpls = {
      "<groupId>0</groupId>", 
      "<artifactId>1</artifactId>",
      "<version>2</version>",
      "<packaging>3</packaging>"
  };

  @Override public void toXml(Src src, StringBuilder xml) {
    String ppv = src.p.getProperty(key);
    String[] vals = ppv.split(":");
    Tag tag = Tag.init(src.indent);
    for (int i = 0; i < vals.length; i++) {
      tag.line(
        tmpls[i].replace(
            Integer.toString(i), vals[i].trim()
        )
      );
    }
    xml.append(tag.string());
  }
}
