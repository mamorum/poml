package poml.conv.build.plugin;

import java.util.Map;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;
import poml.tool.Is;
import poml.tool.Put;
import poml.tool.Throw;
import poml.tool.Tmpl;

public class Compiler implements Converter {

  @Override public String name() { return "compiler"; }

  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> map = poml.conf.map(name(), false);
    Put.defaults("ver", "3.5.1", map);
    if (!Is.in(ks, map)) Throw.noKv(name(), ks);
    Tmpl.render(
      "/conv/build/plugin/compiler.tmpl",
      map, xml
    );
  }
  private static final String[] ks = {
    "source", "target"  // required
  };
}
