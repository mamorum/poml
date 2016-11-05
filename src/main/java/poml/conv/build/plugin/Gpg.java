package poml.conv.build.plugin;

import java.util.Map;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;
import poml.tool.Func.Put;
import poml.tool.Tmpl;

public class Gpg implements Converter {
  
  @Override public String name() { return "gpg"; }

  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> map = poml.conf.map(name());
    Put.defaults("ver", "1.6", map);
    Tmpl.render(
      "/converter/build/plugin/gpg.tmpl",
      map, xml
    );
  }
}
