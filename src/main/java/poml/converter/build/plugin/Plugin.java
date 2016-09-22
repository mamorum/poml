package poml.converter.build.plugin;

import java.util.Map;

import poml.Converter;
import poml.Dst;
import poml.Src;

public abstract class Plugin extends Converter {

  public void printStart(Dst dst) {
    dst.out.println(sp6 + "<plugin>");
  }
  public void printEnd(Dst dst) {
    dst.out.println(sp6 + "</plugin>");
  }
  
  // -> version, extensions, inherited
  public void printVerExIn(
    Src src, Dst dst, String defaultVer
  ) {
    Map<String, String> prop = src.propMap(name());
    putDefault("version", defaultVer, prop);
    dst.printKvTags(sp8, prop);
  }
  
  // -> configuration
  public void printConf(Src src, Dst dst) {
    Map<String, String> prop = src.propMap(name() + ".conf");
    if (prop.size() == 0) return;
    dst.out.println(sp8 + "<configuration>");
    dst.printKvTags(sp10, prop);
    dst.out.println(sp8 + "</configuration>");
  }
}
