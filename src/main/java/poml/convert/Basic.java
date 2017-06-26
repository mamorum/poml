package poml.convert;

import poml.io.Poml;
import poml.io.Xml;

public class Basic {
  public static final String
    pkg="pkg", parent="parent",
    depend="depend", properties = "properties";

  public static void all(Poml in, Xml out) {
    if (in.conf.has(pkg)) {out.nl(); pkg(in, out);}
    if (in.conf.has(parent)) {out.nl(); parent(in, out);}
    if (in.conf.has(depend)) {
      out.nl();
      out.line("  <dependencies>");
      depend(in, out);
      out.line("  </dependencies>");
    }
    if (in.conf.has(properties)) {out.nl(); properties(in, out);}
  }

  private static final String
    groupId="groupId", artifactId="artifactId", version="version";

  //-> pkg=v:v:v...
  private static final String[] pkgTags =
    {groupId, artifactId, version, "packaging"};
  public static void pkg(Poml in, Xml out) {
    String val = in.conf.val(pkg);
    String[] v = val.split(":");
    out.tags(Xml.sp2, pkgTags, v);
  }

  //-> parent=v:v:v...
  private static final String[] parentTags =
    {groupId, artifactId, version, "relativePath"};
  public static void parent(Poml in, Xml out) {
    String val = in.conf.val(parent);
    String[] v = val.split(":");
    out.line("  <parent>");
    out.tags(Xml.sp4, parentTags, v);
    out.line("  </parent>");
  }

  //-> depend=lib, lib... (lib=v:v:v...)
  private static final String[] depTags =
    {groupId, artifactId, version, "scope", "optional", "type"};
  public static void depend(Poml in, Xml out) {
    for (String lib: in.conf.csv(depend)) {
      String[] v = lib.split(":");
      out.line("    <dependency>");
      out.tags(Xml.sp6, depTags, v);
      out.line("    </dependency>");
    }
  }

  // -> properties=k>v, k>v ...
  public static final String
    enc="&encoding>", javac="&compiler>";
  public static void properties(Poml in, Xml out) {
    out.line("  <properties>");
    for (String kv: in.conf.csv(properties)) {
      if (kv.startsWith(enc)) enc(out, v(kv));
      else if (kv.startsWith(javac)) javac(out, v(kv));
      else out.kv(Xml.sp4, kv);
    }
    out.line("  </properties>");
  }
  /// properties=&encoding>v
  private static final String
    sse="    <project.build.sourceEncoding>",
    ese="</project.build.sourceEncoding>",
    soe="    <project.reporting.outputEncoding>",
    eoe="</project.reporting.outputEncoding>";
  private static void enc(Xml out, String v) {
    out.txt(sse).txt(v).line(ese);
    out.txt(soe).txt(v).line(eoe);
  }
  /// properties=&compiler>v
  private static final String
    scs="    <maven.compiler.source>",
    ecs="</maven.compiler.source>",
    sct="    <maven.compiler.target>",
    ect="</maven.compiler.target>";
  private static void javac(Xml out, String v) {
    out.txt(scs).txt(v).line(ecs);
    out.txt(sct).txt(v).line(ect);
  }
  private static String v(String kv) {
    return kv.substring(kv.indexOf('>')+1);
  }
}
