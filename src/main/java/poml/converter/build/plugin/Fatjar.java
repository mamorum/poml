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
    Assert.exists("mainClass", map, name());
    map.put(
      "conf+",
      poml.conf.tag("fatjar.conf+", sp8)
    );
    map.put(
      "archive+",
      poml.conf.tag("fatjar.conf.archive+", sp10)
    );
    Tmpl.render(
      "/converter/build/plugin/fatjar.tmpl",
      map, xml
    );
  }
}
