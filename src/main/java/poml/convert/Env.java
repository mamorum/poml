package poml.convert;

import poml.io.Poml;
import poml.io.Xml;

public class Env {
  public static final String
    issue="issue", scm="scm", dist="dist";

  public static void all(Poml in, Xml out) {
    if (in.conf.has(issue)) {out.nl(); issue(in, out);}
    if (in.conf.has(scm)) {out.nl(); scm(in, out);}
    if (in.conf.has(dist)) {out.nl(); dist(in, out);}
  }

  //-> issue=k>v, k>v ...
  public static void issue(Poml in, Xml out) {
    String kv[] = in.conf.csv(issue);
    out.line("  <issueManagement>");
    out.kv(Xml.sp4, kv);
    out.line("  </issueManagement>");
  }

  //-> scm=k>v, k>v ...
  public static void scm(Poml in, Xml out) {
    String[] kv = in.conf.csv(scm);
    out.line("  <scm>");
    out.kv(Xml.sp4, kv);
    out.line("  </scm>");
  }

  //-> dist=&ossrh
  public static void dist(Poml in, Xml out) {
    String val = in.conf.val(dist);
    if ("&ossrh".equals(val)) {
      out.line("  <distributionManagement>");
      out.line("    <snapshotRepository>");
      out.line("      <id>ossrh</id>");
      out.line("      <url>https://oss.sonatype.org/content/repositories/snapshots</url>");
      out.line("    </snapshotRepository>");
      out.line("    <repository>");
      out.line("      <id>ossrh</id>");
      out.line("      <url>https://oss.sonatype.org/service/local/staging/deploy/maven2/</url>");
      out.line("    </repository>");
      out.line("  </distributionManagement>");
    }
  }
}
