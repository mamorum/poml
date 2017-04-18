package poml.conv.more;

import java.util.Map;

import poml.conv.Converter;
import poml.io.Poml;
import poml.io.Xml;

public class Developer implements Converter{

  @Override public String name() { return "developer"; }

  @Override public void convert(Poml poml, Xml xml) {
    for (String dev: poml.conf.vals(name())) {
      xml.out.add("    <developer>").nl();
      Map<String, String> val = poml.conf.map(dev, false);
      for (String k: keys) xml.outTag(sp6, k, val.get(k));
      xml.out.add("    </developer>").nl();
    }
  }
  private static String[] keys =  {"id", "name", "email", "url"};
}