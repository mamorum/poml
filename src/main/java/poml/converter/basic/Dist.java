package poml.converter.basic;

import poml.Converter;
import poml.Dst;
import poml.Src;

public class Dist implements Converter {

  public String key() { return "dist"; }

  private static final String[] tmpls = {
      "  <groupId>0</groupId>", 
      "  <artifactId>1</artifactId>",
      "  <version>2</version>",
      "  <packaging>3</packaging>"
  };

  public void convert(Src src, Dst dst) {
    String[] vals = src.propList(key(), ":");
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
