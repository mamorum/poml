package poml.convert;

import java.util.Map;

import poml.Poml;
import poml.Throw;
import poml.Xml;

public class Basic {
  public static final String
    pkg="pkg", parent="parent",
    dep="depend", props = "properties",
    sp4="    ";

  public static void all(Poml in, Xml out) {
    if (in.conf.has(pkg)) {out.nl(); pkg(in, out);}
    if (in.conf.has(parent)) {out.nl(); parent(in, out);}
    if (in.conf.has(dep)) {
      out.nl();
      out.line("  <dependencies>");
      depend(in, out);
      out.line("  </dependencies>");
    }
    if (in.conf.has(props)) {out.nl(); properties(in, out);}
  }

  // -> pkg
  private static final String[] pkgTags = {
    "groupId", "artifactId", "version",  // required
    "packaging"  // optional
  };
  public static void pkg(Poml in, Xml out) {
    String val = in.conf.val(pkg);
    String[] vals = val.split(":");
    if (vals.length < 3) {
      Throw.badConf(pkg, val);
    }
    out.tags("  ", pkgTags, vals);
  }

  // -> parent
  private static final String[] parentTags = {
    "groupId", "artifactId", "version",  // required
  };
  public static void parent(Poml in, Xml out) {
    String val = in.conf.val(parent);
    String[] vals = val.split(":");
    if (vals.length < 3) {
      Throw.badConf(parent, val);
    }
    out.line("  <parent>");
    out.tags(sp4, parentTags, vals);
    out.line("  </parent>");
  }

  // -> depend
  private static final String[] depTags = {
    "groupId", "artifactId", // required
    "version", "scope", "optional", "type"  // optional
  };
  public static void depend(Poml in, Xml out) {
    for (String lib: in.conf.vals(dep)) {
      String[] vals = lib.split(":");
      if (vals.length < 2) {
        Throw.badConf(dep, lib);
      }
      out.line("    <dependency>");
      out.tags("      ", depTags, vals);
      out.line("    </dependency>");
    }
  }

  // -> properties
  public static final String
    enc="&encoding", jdk="&compiler";
  public static void properties(Poml in, Xml out) {
    Map<String, String> kv = in.conf.map(props);
    out.line("  <properties>");
    for (String k: kv.keySet()) {
      if (enc.equals(k)) enc(out, kv.get(k));
      else if (jdk.equals(k)) javac(out, kv.get(k));
      else out.tag(sp4, k, kv.get(k));
    }
    out.line("  </properties>");
  }
  private static void enc(Xml out, String v) {
    out.tag(sp4, "project.build.sourceEncoding", v);
    out.tag(sp4, "project.reporting.outputEncoding", v);
  }
  private static void javac(Xml out, String v) {
    out.tag(sp4, "maven.compiler.source", v);
    out.tag(sp4, "maven.compiler.target", v);
  }
}
