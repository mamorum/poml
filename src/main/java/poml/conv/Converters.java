package poml.conv;

import java.util.ArrayList;
import java.util.HashMap;

import poml.conv.basic.Depends;
import poml.conv.basic.Pkg;
import poml.conv.basic.Property;
import poml.conv.build.plugin.Compiler;
import poml.conv.build.plugin.Exec;
import poml.conv.build.plugin.Fatjar;
import poml.conv.build.plugin.Gpg;
import poml.conv.build.plugin.Javadoc;
import poml.conv.build.plugin.Source;
import poml.conv.env.Dist;
import poml.conv.more.Info;
import poml.conv.project.Model4;
import poml.in.Poml;
import poml.out.Xml;

public class Converters {

  private static final class Grp {
    private static final Converter[] prj = {
      new Model4.Start(), new Model4.End()
    };
    private static final Converter[] basic = {
      new Pkg(), new Depends(), Depends.depend,
      new Property()
    };
    private static final Converter[] plgin = {
      new Gpg(), new Compiler(), new Source(),
      new Javadoc(), new Exec(), new Fatjar()
    };
    private static final Converter[] more = {
      new Info()
    };
    private static final Converter[] env = {
      new Dist()
    };  
  }
  private static final HashMap<String, Converter>
    all = new HashMap<>();
  static {
    put(Grp.prj); put(Grp.basic); put(Grp.plgin);
    put(Grp.more); put(Grp.env);
  }
  private static void put(Converter[] cs) {
    for (Converter c: cs) all.put(c.name(), c);
  }
  
  public static Converter get(String name) {
    Converter c = all.get(name);
    if (c == null) throw new RuntimeException(
      "Converter not found for {{" + name + "}}"
    );
    return c;
  }

  public static void convert(Poml poml, Xml xml) {
    Grp.prj[0].convert(poml, xml);
    convert(Grp.basic, poml, xml);
    convertPlugins(poml, xml);
    convert(Grp.more, poml, xml);
    convert(Grp.env, poml, xml);
    Grp.prj[1].convert(poml, xml);
  }
  private static void convert(
    Converter[] cs, Poml poml, Xml xml
  ) {
    for (Converter c: cs) {
      if (poml.conf.has(c.name())) {
        xml.out.println();
        c.convert(poml, xml);
      }
    }
  }
  private static void convertPlugins(Poml poml, Xml xml) {
    ArrayList<Converter> targets = new ArrayList<>();
    for (Converter c: Grp.plgin) {
      if (poml.conf.has(c.name())) {
        targets.add(c);
      }
    }
    if (targets.size() == 0) return;
    
    // output xml.
    xml.out.println();
    xml.out.println("  <build>");
    xml.out.println("    <plugins>");
    for (Converter c: targets) {
      c.convert(poml, xml);
    }
    xml.out.println("    </plugins>");
    xml.out.println("  </build>");
  }
}
