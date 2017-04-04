package poml.conv.more;

import java.util.Map;

import poml.conv.Converter;
import poml.io.Poml;
import poml.io.Xml;

public class Developers implements Converter{

  @Override public String name() { return "developers"; }

  @Override public void convert(Poml poml, Xml xml) {
    String[] devs = poml.conf.vals(name());
    xml.out.add("  <developers>").nl();
    for (String dev: devs) {
      xml.out.add("    <developer>").nl();
      addDeveloper(poml.conf.map(dev, false), xml);
      xml.out.add("    </developer>").nl();
    }
    xml.out.add("  </developers>").nl();
  }
  private void addDeveloper(Map<String, String> conf, Xml xml) {
    for (String k: keys) xml.outTag(sp6, k, conf.get(k));
  }
  private static String[] keys =  {"id", "name", "email", "url"};
}