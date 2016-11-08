package poml.conv.build.plugin;

import java.util.Map;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;
import poml.tool.Func.Put;
import poml.tool.Is;
import poml.tool.Throw;
import poml.tool.Tmpl;

public class Compiler implements Converter {

  @Override public String name() { return "compiler"; }

  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> map = poml.conf.map(name(), false);
    Put.defaults("ver", "3.5.1", map);
    if (!Is.in(keys, map)) Throw.noKv(name(), keys);
    Tmpl.render(
      "/converter/build/plugin/compiler.tmpl",
      map, xml
    );
  }
  
  private static final String[] keys = {"source", "target"};
}
