package poml.conv;

import java.util.HashMap;

import poml.Throw;
import poml.conv.basic.Depend;
import poml.conv.basic.Parent;
import poml.conv.basic.Pkg;
import poml.conv.basic.Properties;
import poml.conv.build.Plugin;
import poml.conv.env.Dist;
import poml.conv.env.Issue;
import poml.conv.env.Scm;
import poml.conv.more.Developer;
import poml.conv.more.Info;
import poml.conv.more.License;
import poml.conv.prj.Model4;
import poml.in.Poml;
import poml.out.Xml;

public class Converters {

  // -> for "No Layout Section"
  public static void convert(Poml poml, Xml xml) {
    start.convert(poml, xml);
    convertBasic(poml, xml);
    convertBuild(poml, xml);
    convertMore(poml, xml);
    convertEnv(poml, xml);
    end.convert(poml, xml);
  }
  private static void convert(
    Converter c, Poml poml, Xml xml
  ) {
    if (poml.conf.has(c.name())) {
      xml.nl();
      c.convert(poml, xml);
    }
  }
  private static void convert(
      Converter c, Poml poml, Xml xml,
      String pre, String post
    ) {
      if (poml.conf.has(c.name())) {
        xml.nl();
        xml.line(pre);
        c.convert(poml, xml);
        xml.line(post);
      }
  }
  private static void convertBasic(Poml poml, Xml xml) {
    convert(pkg, poml, xml);
    convert(parent, poml, xml);
    convert(
      depend, poml, xml,
      "  <dependencies>", "  </dependencies>"
    );
    convert(props, poml, xml);
  }
  private static void convertBuild(Poml poml, Xml xml) {
    if (poml.conf.has(plg.name())) {
      xml.nl();
      xml.line("  <build>");
      xml.line("    <plugins>");
      plg.convert(poml, xml);
      xml.line("    </plugins>");
      xml.line("  </build>");
    }
  }
  private static void convertMore(Poml poml, Xml xml) {
    convert(info, poml, xml);
    convert(
      license, poml, xml, "  <licenses>", "  </licenses>"
    );
    convert(
      dev, poml, xml, "  <developers>", "  </developers>"
    );
  }
  private static void convertEnv(Poml poml, Xml xml) {
    convert(issue, poml, xml);
    convert(scm, poml, xml);
    convert(dist, poml, xml);
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
  private static final Converter
    start = new Model4.Start(), end = new Model4.End(),
    pkg = new Pkg(), parent = new Parent(),
    depend = new Depend(), props = new Properties(),
    plg = new Plugin(),
    info = new Info(), license = new License(),
    dev = new Developer(),
    issue = new Issue(), scm = new Scm(),
    dist = new Dist();
  private static final Converter[] convs = {
    start, end, // prj
    pkg, parent, depend, props, // basic
    plg, // build
    info, license, dev, // more
    issue, scm, dist // env
  };
  private static final HashMap<String, Converter>
    all = new HashMap<>();
  static { for (Converter c: convs)  all.put(c.name(), c); }
}