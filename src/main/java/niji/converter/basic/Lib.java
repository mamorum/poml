package niji.converter.basic;

import niji.Dst;
import niji.Src;
import niji.Converters.Converter;

public class Lib implements Converter {

  public static String key = "lib";
  
  static String[] tmpls = {
      "      <groupId>0</groupId>", 
      "      <artifactId>1</artifactId>",
      "      <version>2</version>",
      "      <scope>3</scope>",
      "      <optional>4</optional>",
      "      <type>5</type>"
  };

  @Override public void convert(Src src, Dst dst) {
    dst.out.println("  <dependencies>");
    for (String lib: src.propertyList(key)) {
      addChild(lib.trim(), dst);
    }
    dst.out.println("  </dependencies>");
  }

  public void addChild(String lib, Dst dst) {
    dst.out.println("    <dependency>");
    String[] vals = lib.split(":");
    for (int i = 0; i < vals.length; i++) {
      dst.out.println(tag(i, vals[i]));
    }
    dst.out.println("    </dependency>");
  }

  public String tag(int index, String val) {
    return tmpls[index].replace(
        Integer.toString(index),
        val.trim()
    );
  }
}
