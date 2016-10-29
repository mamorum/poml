package poml.conv.build.plugin;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;
import poml.tool.Tmpl;

public class Source implements Converter {

  @Override public String name() { return "source"; }

  @Override public void convert(Poml poml, Xml xml) {
    Tmpl.render(
      "/converter/build/plugin/source.tmpl",
      poml.conf.map(name()), xml
    );
  }
}
