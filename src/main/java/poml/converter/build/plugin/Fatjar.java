package poml.converter.build.plugin;

import java.util.Map;

import poml.Converter;
import poml.Dst;
import poml.Src;
import poml.converter.Assert;
import poml.converter.Tmpl;

public class Fatjar extends Converter {
  
  @Override public String name() { return "fatjar"; }
  
  @Override public void convert(Src src, Dst dst) {
    Map<String, String> map = src.propMap(name());
    Assert.exist(
      map.get("mainClass"),
      "mainClass is required @fatjar."
    );
    putDefault("ver", "2.6", map);
    dst.out.print(
      Tmpl.render("/tmpl/fatjar.tmpl", map)
    );
  }
  
}
