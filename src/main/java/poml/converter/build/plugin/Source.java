package poml.converter.build.plugin;

import poml.Converter;
import poml.in.Poml;
import poml.out.Xml;
import poml.tool.converter.Tmpl;

public class Source implements Converter {

  @Override public String name() { return "source"; }

  @Override public void convert(Poml poml, Xml xml) {
    Tmpl.render(
      "/converter/build/plugin/source.tmpl",
      poml.conf.map(name()), xml
    );
  }
}
