package poml.conv;

import java.util.HashMap;

import poml.conv.basic.Depends;
import poml.conv.basic.Parent;
import poml.conv.basic.Pkg;
import poml.conv.basic.Properties;
import poml.conv.build.Plugin;
import poml.conv.env.Dist;
import poml.conv.env.Issue;
import poml.conv.env.Scm;
import poml.conv.more.Developers;
import poml.conv.more.Info;
import poml.conv.more.Licenses;
import poml.conv.prj.Model4;
import poml.io.Poml;
import poml.io.Xml;
import poml.tool.Throw;

public class Converters {

  // -> for "No Layout Section"
  public static void convert(Poml poml, Xml xml) {
    start.convert(poml, xml);
    for (Converter c: basic) convert(c, poml, xml);
    convertPlugins(poml, xml);
    for (Converter c: more) convert(c, poml, xml);
    for (Converter c: env) convert(c, poml, xml);
    end.convert(poml, xml);
  }
  private static void convert(
    Converter c, Poml poml, Xml xml
  ) {
    if (poml.conf.has(c.name())) {
      xml.out.nl();
      c.convert(poml, xml);
    }
  }
  private static void convertPlugins(Poml poml, Xml xml) {
    if (poml.conf.has(plg.name())) {
      xml.out.nl();
      xml.out.add("  <build>").nl();
      xml.out.add("    <plugins>").nl();
      plg.convert(poml, xml);
      xml.out.add("    </plugins>").nl();
      xml.out.add("  </build>").nl();
    }
  }

  // -> for "Layout Section"
  public static void convert(
    String name, Poml poml, Xml xml
  ) {
    Converter c = all.get(name);
    if (c == null) Throw.noConv(name);
    c.convert(poml, xml);
  }

  // -> all converters
  private static final HashMap<String, Converter>
    all = new HashMap<>();
  private static final Converter[]
    basic = {
      new Pkg(), new Parent(), new Depends(),
      new Properties()},
    more = {
      new Info(), new Licenses(), new Developers()},
    env = {
      new Issue(), new Scm(), new Dist()};
  private static final Converter
    start=new Model4.Start(), end=new Model4.End(),
    plg = new Plugin();
  static {
    for (Converter c: basic) put(c);
    for (Converter c: more) put(c);
    for (Converter c: env) put(c);
    put(start); put(end); put(plg);
    put(Depends.depend);
  }
  private static void put(Converter c) {
    all.put(c.name(), c);
  }
}