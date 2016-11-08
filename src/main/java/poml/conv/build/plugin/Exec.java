package poml.conv.build.plugin;

import java.util.Map;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;
import poml.tool.Is;
import poml.tool.Put;
import poml.tool.Throw;
import poml.tool.Tmpl;

public class Exec implements Converter {
  
  @Override public String name() { return "exec"; }

  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> map = poml.conf.map(name(), false);
    if (!Is.in(k, map)) Throw.noKv(name(), k);
    Put.defaults("ver", "1.5.0", map);
    Tmpl.render(
      "/conv/build/plugin/exec.tmpl",
      map, xml
    );
  }
  private static final String k = "mainClass"; // required
}
