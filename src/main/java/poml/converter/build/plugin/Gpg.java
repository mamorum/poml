package poml.converter.build.plugin;

import poml.Converter;
import poml.in.Poml;
import poml.out.Xml;
import poml.tool.converter.Tmpl;

public class Gpg implements Converter {
  
  @Override public String name() { return "gpg"; }

  @Override public void convert(Poml poml, Xml xml) {
    Tmpl.render(
      "/converter/build/plugin/gpg.tmpl",
      poml.conf.map(name()), xml
    );
  }
}
