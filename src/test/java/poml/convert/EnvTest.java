package poml.convert;

import org.junit.Test;

import poml.UtCase;

public class EnvTest extends UtCase {

  //-> issue
  @Test public void issue_sys2url() {
    poml(
      "issue=" + nl +
      "  system>GitHub Issues," + nl +
      "  url>https://github.com/mamorum/poml/issues" + nl
    );
    Env.issue(poml, xml);
    xml(
      "  <issueManagement>" + nl +
      "    <system>GitHub Issues</system>" + nl +
      "    <url>https://github.com/mamorum/poml/issues</url>" + nl +
      "  </issueManagement>" + nl
    );
  }
  @Test public void issue_url() {
    poml(
      "issue=url>https://github.com/mamorum/poml/issues"
    );
    Env.issue(poml, xml);
    xml(
      "  <issueManagement>" + nl +
      "    <url>https://github.com/mamorum/poml/issues</url>" + nl +
      "  </issueManagement>" + nl
    );
  }

  //-> scm
  @Test public void scm_connection2url() {
    poml(
      "scm=" + nl +
      "  connection>scm:git:https://github.com/mamorum/poml.git," + nl +
      "  developerConnection>scm:git:git@github.com:mamorum/poml.git," + nl +
      "  tag>HEAD, url>https://github.com/mamorum/poml" + nl
    );
    Env.scm(poml, xml);
    xml(
      "  <scm>" + nl +
      "    <connection>scm:git:https://github.com/mamorum/poml.git</connection>" + nl +
      "    <developerConnection>scm:git:git@github.com:mamorum/poml.git</developerConnection>" + nl +
      "    <tag>HEAD</tag>" + nl +
      "    <url>https://github.com/mamorum/poml</url>" + nl +
      "  </scm>" + nl
    );
  }
  @Test public void scm_url() {
    poml(
      "scm=" + nl +
      "  url>https://github.com/mamorum/poml/" + nl
    );
    Env.scm(poml, xml);
    xml(
      "  <scm>" + nl +
      "    <url>https://github.com/mamorum/poml/</url>" + nl +
      "  </scm>" + nl
    );
  }

  //-> dist
  @Test public void dist_ossrh() {
    poml(
      "dist=&ossrh"
    );
    Env.dist(poml, xml);
    xml(
      "  <distributionManagement>" + nl +
      "    <snapshotRepository>" + nl +
      "      <id>ossrh</id>" + nl +
      "      <url>https://oss.sonatype.org/content/repositories/snapshots</url>" + nl +
      "    </snapshotRepository>" + nl +
      "    <repository>" + nl +
      "      <id>ossrh</id>" + nl +
      "      <url>https://oss.sonatype.org/service/local/staging/deploy/maven2/</url>" + nl +
      "    </repository>" + nl +
      "  </distributionManagement>" + nl
    );
  }
}
