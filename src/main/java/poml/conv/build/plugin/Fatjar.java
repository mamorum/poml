package poml.conv.build.plugin;

import java.util.Map;

import poml.conv.Converter;
import poml.io.Poml;
import poml.io.Xml;
import poml.tool.Put;
import poml.tool.Is;
import poml.tool.Throw;
import poml.tool.Tmpl;

public class Fatjar implements Converter {

  private static final String name = "&fatjar";
  @Override public String name() { return name; }
  
  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> map = poml.conf.map(name(), false);
    if (!Is.in(k, map)) Throw.noKv(name(), k);
    Put.defaults("ver", "2.6", map);
    Put.defaults("jarName", "${project.artifactId}", map);
    if (poml.conf.has(confPlus)) map.put(
      "conf+", poml.conf.tag(confPlus, sp8)
    );
    if (poml.conf.has(archPlus)) map.put(
      "archive+", poml.conf.tag(archPlus, sp10)
    );
    Tmpl.render(
      "/conv/build/plugin/fatjar.tmpl",
      map, xml
    );
  }
  private static final String k = "mainClass"; // required
  private static final String confPlus = name + ".conf+";
  private static final String archPlus = name + ".conf.archive+";
}
