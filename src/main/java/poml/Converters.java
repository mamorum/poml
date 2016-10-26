package poml;

import java.util.ArrayList;
import java.util.HashMap;

import poml.converter.basic.Depend;
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

  private static final HashMap<String, Converter>
    all = new HashMap<>();

  private static final Group grp = new Group();
  static {
    put(grp.prj); put(grp.basic); put(grp.plgin);
    put(grp.more); put(grp.env);
  }
  public static void put(Converter[] cs) {
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
    grp.prj[0].convert(poml, xml);
    delegate(grp.basic, poml, xml);
    plugins(poml, xml);
    delegate(grp.more, poml, xml);
    delegate(grp.env, poml, xml);
    grp.prj[1].convert(poml, xml);
  }
  private static void delegate(
    Converter[] cs, Poml poml, Xml xml
  ) {
    for (Converter c: cs) {
      String config = poml.conf.val(c.name());
      if (config == null) continue;
      xml.out.println();
      c.convert(poml, xml);
    }
  }
  private static void plugins(Poml poml, Xml xml) {
    ArrayList<Converter> targets = new ArrayList<>();
    for (Converter c: grp.plgin) {
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
  
  private static class Group {
    private final Converter[] prj = {
        new Model4.Start(), new Model4.End()
    };
    private final Converter[] basic = {
        new Pkg(), new Depends(), new Depend(),
        new Property()
    };
    private final Converter[] plgin = {
        new Gpg(), new Compiler(), new Source(),
        new Javadoc(), new Fatjar(), new Exec()
    };
    private final Converter[] more = {
        new Info()
    };
    private final Converter[] env = {
        new Dist()
    };  
  }
}
