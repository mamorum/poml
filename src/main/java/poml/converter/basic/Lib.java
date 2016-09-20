package poml.converter.basic;

import poml.Converter;
import poml.Dst;
import poml.Src;

public class Lib extends Converter {

  public String name() { return "lib"; }
  
  private static final String[] tmpls = {
      "      <groupId>0</groupId>", 
      "      <artifactId>1</artifactId>",
      "      <version>2</version>",
      "      <scope>3</scope>",
      "      <optional>4</optional>",
      "      <type>5</type>"
  };

  @Override public void convert(Src src, Dst dst) {
    dst.out.println("  <dependencies>");
    for (String lib: src.propList(name(), ",")) {
      addLib(lib.trim(), dst);
    }
    dst.out.println("  </dependencies>");
  }

  public void addLib(String lib, Dst dst) {
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
