package poml.conv.build.plugin;

import java.util.Map;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;
import poml.tool.Assert;
import poml.tool.Tmpl;
import poml.tool.Func.Put;

public class Compiler implements Converter {

  @Override public String name() { return "compiler"; }

  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> map = poml.conf.map(name(), false);
    Put.defaults("ver", "3.5.1", map);
    Assert.exist(keys, map, name());
    Tmpl.render(
      "/converter/build/plugin/compiler.tmpl",
      map, xml
    );
  }
  
  private static final String[] keys = {"source", "target"};
}
