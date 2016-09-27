package poml.converter.build.plugin;

import java.util.Map;

import poml.Converter;
import poml.Dst;
import poml.Src;
import poml.tools.Assert;
import poml.tools.Tmpl;

public class Javac implements Converter {

  @Override public String name() { return "javac"; }

  @Override public void convert(Src src, Dst dst) {
    Map<String, String> map = src.propMap(name());
    Assert.exists(
      new String[]{"source", "target"},
      map, name()
    );
    Put.defaults("ver", "3.5.1", map);
    Tmpl.render("/tmpl/javac.tmpl", map, dst);
  }
}
