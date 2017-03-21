package poml.conv;

import java.util.ArrayList;
import java.util.HashMap;

import poml.conv.basic.Depends;
import poml.conv.basic.Parent;
import poml.conv.basic.Pkg;
import poml.conv.basic.Property;
import poml.conv.build.plugin.Compiler;
import poml.conv.build.plugin.Exec;
import poml.conv.build.plugin.Fatjar;
import poml.conv.build.plugin.Gpg;
import poml.conv.build.plugin.Javadoc;
import poml.conv.build.plugin.Sbp;
import poml.conv.build.plugin.Source;
import poml.conv.env.Dist;
import poml.conv.env.Issue;
import poml.conv.env.Scm;
import poml.conv.more.Info;
import poml.conv.project.Model4;
import poml.in.Poml;
import poml.out.Xml;
import poml.tool.Throw;

public class Converters {

  // -> for "No Layout Section"
  public static void convert(Poml poml, Xml xml) {
    start.convert(poml, xml);
    for (Converter c: basic) convert(c, poml, xml);
    convertPlugins(poml, xml);
    convert(info, poml, xml);
    for (Converter c: env) convert(c, poml, xml);
    end.convert(poml, xml);
  }
  private static void convert(
    Converter c, Poml poml, Xml xml
  ) {
    if (poml.conf.has(c.name())) {
      xml.println();
      c.convert(poml, xml);
    }
  }
  private static void convertPlugins(Poml poml, Xml xml) {
    ArrayList<Converter> targets = new ArrayList<>();
    for (Converter c: plgin) {
      if (poml.conf.has(c.name())) {
        targets.add(c);
      }
    }
    if (targets.size() == 0) return;
    
    // output xml.
    xml.println();
    xml.println("  <build>");
    xml.println("    <plugins>");
    for (Converter c: targets) {
      c.convert(poml, xml);
    }
    xml.println("    </plugins>");
    xml.println("  </build>");
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
      new Property()},
    plgin = {
      new Gpg(), new Compiler(),
      new Source(), new Javadoc(),
      new Exec(), new Fatjar(), new Sbp()},
    env = {
      new Issue(), new Scm(), new Dist()};
  private static final Converter
    start=new Model4.Start(), end=new Model4.End(),
    info = new Info();
  static {
    for (Converter c: basic) put(c);
    for (Converter c: env) put(c);
    for (Converter c: plgin) put(c);
    put(start); put(end); put(info);
    put(Depends.depend);
  }
  private static void put(Converter c) {
    all.put(c.name(), c);
  }
}