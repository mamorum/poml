package poml.convert;

import poml.io.Conf;
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

  //-> issue (system, url)
  public static void issue(Poml in, Xml out) {
    out.line("  <issueManagement>");
    out.kvs(Xml.sp4, in.conf.vals(issue), issue);
    out.line("  </issueManagement>");
  }

  //-> scm (connection, developerConnection, tag, url)
  public static void scm(Poml in, Xml out) {
    out.line("  <scm>");
    out.kvs(Xml.sp4, in.conf.vals(scm), scm);
    out.line("  </scm>");
  }

  //-> dist
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
    else Conf.err(dist, val);
  }
}
