package poml.converter.build.plugin;

import java.util.Map;

import poml.Converter;
import poml.in.Poml;
import poml.out.Xml;
import poml.tool.converter.Assert;
import poml.tool.converter.Tmpl;

public class Fatjar implements Converter {

  @Override public String name() { return "fatjar"; }

  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> map = poml.conf.map(name());
    Assert.exists(
        new String[] {"mainClass", "jarName"},
        map, name()
    );
    Put.defaults("ver", "2.6", map);
    xml.out.print(Tmpl.render(
      "/converter/build/plugin/fatjar.tmpl",
      map
    ));
  }
}
