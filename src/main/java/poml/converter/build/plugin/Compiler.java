package poml.converter.build.plugin;

import java.util.Map;

import poml.Converter;
import poml.in.Poml;
import poml.out.Xml;
import poml.tool.converter.Assert;
import poml.tool.converter.Tmpl;

public class Compiler implements Converter {

  @Override public String name() { return "compiler"; }

  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> map = poml.conf.map(name());
    Assert.exists(
      new String[]{"source", "target"},
      map, name()
    );
    Put.defaults("ver", "3.5.1", map);
    xml.out.print(Tmpl.render(
      "/converter/build/plugin/compiler.tmpl",
      map
    ));
  }
}
