package poml.convert;

import poml.io.Poml;
import poml.io.Xml;

public class Basic {
  public static final String //-> config name
    pkg="pkg", parent="parent",
    dep="depend", prop = "properties";
  public static final String //-> tag name
    grpId="groupId", artId="artifactId", ver="version";

  public static void all(Poml in, Xml out) {
    if (in.conf.has(pkg)) {out.nl(); pkg(in, out);}
    if (in.conf.has(parent)) {out.nl(); parent(in, out);}
    if (in.conf.has(dep)) {
      out.nl();
      out.line("  <dependencies>");
      depend(in, out);
      out.line("  </dependencies>");
    }
    if (in.conf.has(prop)) {out.nl(); properties(in, out);}
  }

  // -> pkg=v:v:v...
  private static final String[] pkgTags =
    {grpId, artId, ver, "packaging"};
  public static void pkg(Poml in, Xml out) {
    String[] v = in.conf.clnsv(pkg);
    out.tags(Xml.sp2, pkgTags, v);
  }

  // -> parent=v:v:v
  private static final String[] parentTags =
    {grpId, artId, ver};
  public static void parent(Poml in, Xml out) {
    String[] v = in.conf.clnsv(parent);
    out.line("  <parent>");
    out.tags(Xml.sp4, parentTags, v);
    out.line("  </parent>");
  }

  // -> depend=v:v:v..., v:v:v...
  private static final String[] depTags =
    {grpId, artId, ver, "scope", "optional", "type"};
  public static void depend(Poml in, Xml out) {
    for (String lib: in.conf.csv(dep)) {
      String[] vals = lib.split(":");
      out.line("    <dependency>");
      out.tags(Xml.sp6, depTags, vals);
      out.line("    </dependency>");
    }
  }

  // -> properties=k>v, k>v ...
  public static final String
    enc="&encoding>", javac="&compiler>";
  public static void properties(Poml in, Xml out) {
    out.line("  <properties>");
    for (String kv: in.conf.csv(prop)) {
      if (kv.startsWith(enc)) enc(out, v(kv));
      else if (kv.startsWith(javac)) javac(out, v(kv));
      else out.kv(Xml.sp4, kv, prop);
    }
    out.line("  </properties>");
  }
  private static void enc(Xml out, String v) {
    out.tag(Xml.sp4, "project.build.sourceEncoding", v);
    out.tag(Xml.sp4, "project.reporting.outputEncoding", v);
  }
  private static void javac(Xml out, String v) {
    out.tag(Xml.sp4, "maven.compiler.source", v);
    out.tag(Xml.sp4, "maven.compiler.target", v);
  }
  private static String v(String kv) {
    return kv.substring(kv.indexOf('>')+1);
  }
}
