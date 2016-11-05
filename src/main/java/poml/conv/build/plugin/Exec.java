package poml.conv.build.plugin;

import java.util.Map;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;
import poml.tool.Assert;
import poml.tool.Tmpl;

public class Exec implements Converter {
  
  @Override public String name() { return "exec"; }

  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> map = poml.conf.map(name());
    Assert.exist("mainClass", map, name());
    Tmpl.render(
      "/converter/build/plugin/exec.tmpl",
      map, xml
    );
  }
}
