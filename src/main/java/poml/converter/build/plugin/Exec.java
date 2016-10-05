package poml.converter.build.plugin;

import java.util.Map;

import poml.Converter;
import poml.Dst;
import poml.Src;
import poml.tools.Assert;
import poml.tools.Tmpl;

public class Exec implements Converter {
  
  @Override public String name() { return "exec"; }

  @Override public void convert(Src src, Dst dst) {
    Map<String, String> map = src.propMap(name());
    Assert.exists("mainClass", map, name());
    Put.defaults("ver", "1.5.0", map);
    Tmpl.render("/tmpl/exec.tmpl", map, dst);
  }
}
