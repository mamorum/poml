package poml.conv.build.plugin;

import java.util.Map;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;
import poml.tool.Assert;
import poml.tool.Tmpl;
import poml.tool.Func.Put;

public class Fatjar implements Converter {

  @Override public String name() { return "fatjar"; }
  
  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> map = poml.conf.map(name(), false);
    Assert.notNull("mainClass", map, name());
    Put.defaults("ver", "2.6", map);
    Put.defaults("jarName", "${project.artifactId}", map);
    if (poml.conf.has(confPlus)) map.put(
      "conf+", poml.conf.tag(confPlus, sp8)
    );
    if (poml.conf.has(archPlus)) map.put(
      "archive+", poml.conf.tag(archPlus, sp10)
    );
    Tmpl.render(
      "/converter/build/plugin/fatjar.tmpl",
      map, xml
    );
  }
  private static final String confPlus = "fatjar.conf+";
  private static final String archPlus = "fatjar.conf.archive+";
}
