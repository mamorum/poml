package poml;

import java.util.ArrayList;
import java.util.HashMap;

import poml.converter.basic.Depends;
import poml.converter.basic.Pkg;
import poml.converter.basic.Property;
import poml.converter.build.plugin.Compiler;
import poml.converter.build.plugin.Exec;
import poml.converter.build.plugin.Fatjar;
import poml.converter.build.plugin.Gpg;
import poml.converter.build.plugin.Javadoc;
import poml.converter.build.plugin.Source;
import poml.converter.env.Dist;
import poml.converter.more.Info;
import poml.converter.project.Model4;
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
      String config = poml.conf.val(c.name());
      if (config == null) continue;
      xml.out.println();
      c.convert(poml, xml);
    }
  }
  private static void convertPlugins(Poml poml, Xml xml) {
    ArrayList<Converter> targets = new ArrayList<>();
    for (Converter c: Grp.plgin) {
      String config = poml.conf.val(c.name());
      if (config == null) continue;
      targets.add(c);
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
