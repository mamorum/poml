package poml.converter.build.plugin;

import java.util.Map;

import poml.Converter;
import poml.in.Poml;
import poml.out.Xml;
import poml.tool.converter.Tmpl;

public class Javadoc implements Converter {
  
  @Override public String name() { return "javadoc"; }

  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> map = poml.conf.map(name());
    Put.defaults("ver", "2.10.4", map);
    xml.out.print(Tmpl.render(
      "/converter/build/plugin/javadoc.tmpl",
      map
    ));
  }
}
