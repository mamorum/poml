package poml.converter.build.plugin;

import java.util.Map;

import poml.Converter;
import poml.Pom;
import poml.Poml;
import poml.tools.converter.Assert;
import poml.tools.converter.Tmpl;

public class Fatjar implements Converter {

  @Override public String name() { return "fatjar"; }

  @Override public void convert(Poml poml, Pom pom) {
    Map<String, String> map = poml.conf.map(name());
    Assert.exists(
        new String[] {"mainClass", "jarName"},
        map, name()
    );
    Put.defaults("ver", "2.6", map);
    Tmpl.render("/tmpl/fatjar.tmpl", map, pom);
  }
}
