package poml.converter.build.plugin;

import java.util.Map;

import poml.Converter;
import poml.Xml;
import poml.Poml;
import poml.tools.converter.Assert;
import poml.tools.converter.Tmpl;

public class Exec implements Converter {
  
  @Override public String name() { return "exec"; }

  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> map = poml.conf.map(name());
    Assert.exists("mainClass", map, name());
    Put.defaults("ver", "1.5.0", map);
    Tmpl.render("/tmpl/exec.tmpl", map, xml);
  }
}
