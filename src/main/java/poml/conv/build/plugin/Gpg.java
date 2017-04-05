package poml.conv.build.plugin;

import java.util.Map;

import poml.conv.Converter;
import poml.io.Poml;
import poml.io.Xml;
import poml.tool.Put;
import poml.tool.Tmpl;

public class Gpg implements Converter {
  
  @Override public String name() { return "&gpg"; }

  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> map = poml.conf.map(name(), true);
    Put.defaults("ver", "1.6", map);
    Tmpl.render(
      "/conv/build/plugin/gpg.tmpl",
      map, xml
    );
  }
}
