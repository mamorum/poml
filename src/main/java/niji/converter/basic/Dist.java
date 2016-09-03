package niji.converter.basic;

import niji.Dst;
import niji.Src;
import niji.Converters.Converter;

public class Dist implements Converter {

  public static String key = "dist";

  static String[] tmpls = {
      "  <groupId>0</groupId>", 
      "  <artifactId>1</artifactId>",
      "  <version>2</version>",
      "  <packaging>3</packaging>"
  };

  @Override public void convert(Src src, Dst dst) {
    String[] vals = src.p(key).split(":");
    for (int i = 0; i < vals.length; i++) {
      dst.out.println(
          tag(i, vals[i])
      );
    }
  }
  
  public String tag(int index, String val) {
    return tmpls[index].replace(
        Integer.toString(index),
        val.trim()
    );
  }
}
