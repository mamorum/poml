package poml.converter.build.plugin;

import java.util.Map;

import poml.Converter;
import poml.in.Poml;
import poml.out.Xml;
import poml.tool.converter.Assert;
import poml.tool.converter.Tmpl;

public class Fatjar implements Converter {

  @Override public String name() { return name; }

  private static final String name = "fatjar";
  private static final String dotConf =  name + ".conf";
  private static final String addConf = dotConf  + "+";
  private static final String addArch = dotConf + ".archive+";
  
  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> map = poml.conf.map(name());
    Assert.exists("mainClass", map, name());
    Put.defaults("ver", "2.6", map);
    Put.defaults("jarName", "${project.artifactId}", map);
    xml.out.print(Tmpl.render(
      "/converter/build/plugin/fatjar/start.tmpl",
      map
    ));
    
    // archive
    String archTags[] = poml.conf.tags(addArch);
    for (String s: archTags) {
      xml.out.print(sp10);
      xml.out.println(s);
    }
    xml.out.println(sp10 + "</archive>");
    
    // configuration
    String confTags[] = poml.conf.tags(addConf);
    for (String s: confTags) {
      xml.out.print(sp8);
      xml.out.println(s);
    }
    
    xml.out.print(Tmpl.text(
      "/converter/build/plugin/fatjar/end.tmpl"
    ));
  }
}
