package poml.converter.build.plugin;

import java.util.Map;

import poml.Converter;
import poml.Dst;
import poml.Src;
import poml.tools.Assert;
import poml.tools.Tmpl;

public class Compiler implements Converter {

  @Override public String name() { return "compiler"; }

  @Override public void convert(Src src, Dst dst) {
    Map<String, String> map = src.propMap(name());
    Assert.exists(
      new String[]{"source", "target"},
      map, name()
    );
    Put.defaults("ver", "3.5.1", map);
    Tmpl.render("/tmpl/compiler.tmpl", map, dst);
  }
}