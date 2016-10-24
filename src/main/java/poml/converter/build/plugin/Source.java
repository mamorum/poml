package poml.converter.build.plugin;

import java.util.Map;

import poml.Converter;
import poml.in.Poml;
import poml.out.Xml;
import poml.tool.converter.Tmpl;

public class Source implements Converter {
  
  @Override public String name() { return "source"; }

  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> map = poml.conf.map(name());
    Put.defaults("ver", "3.0.1", map);
    xml.out.print(Tmpl.render(
      "/converter/build/plugin/source.tmpl",
      map
    ));
  }
}
