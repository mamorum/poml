package poml.converter.build.plugin;

import java.util.Map;

import poml.Converter;
import poml.in.Poml;
import poml.out.Xml;
import poml.tool.converter.Assert;
import poml.tool.converter.Tmpl;

public class Exec implements Converter {
  
  @Override public String name() { return "exec"; }

  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> map = poml.conf.map(name());
    Assert.exists("mainClass", map, name());
    Put.defaults("ver", "1.5.0", map);
    xml.out.print(Tmpl.render(
      "/converter/build/plugin/exec.tmpl",
      map
    ));
  }
}
