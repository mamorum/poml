package poml.convert;

import org.junit.Test;

import poml.UtCase;

public class MoreTest extends UtCase {

  //-> info
  @Test public void info_name2url() {
    poml(
      "info=" + nl +
      "  name>POML, description>POM's Minimal Language," + nl +
      "  url>https://github.com/mamorum/poml" + nl
    );
    More.info(poml, xml);
    xml(
        "  <name>POML</name>" + nl +
        "  <description>POM's Minimal Language</description>" + nl +
        "  <url>https://github.com/mamorum/poml</url>" + nl
    );
  }
  @Test public void info_name2year() {
    poml(
      "info=" + nl +
      "  name>POML, description>POM's Minimal Language," + nl +
      "  url>https://github.com/mamorum/poml, inceptionYear>2016" + nl
    );
    More.info(poml, xml);
    xml(
        "  <name>POML</name>" + nl +
        "  <description>POM's Minimal Language</description>" + nl +
        "  <url>https://github.com/mamorum/poml</url>" + nl +
        "  <inceptionYear>2016</inceptionYear>" + nl
    );
  }
  @Test public void info_single() {
    poml(
      "info=url>https://github.com/mamorum/poml"
    );
    More.info(poml, xml);
    xml(
      "  <url>https://github.com/mamorum/poml</url>" + nl
    );
  }

  //-> license
  @Test public void license_multi() {
    poml(
      "license=&apache2, &mit, $bsd2" + nl +
      "$bsd2=" + nl +
      "  name>The New BSD License," + nl +
      "  url>http://www.opensource.org/licenses/bsd-license.php," + nl +
      "  distribution>repo, comments>The 2-Clause BSD License" + nl
    );
    More.license(poml, xml);
    xml(
      "    <license>" + nl +
      "      <name>The Apache License, Version 2.0</name>" + nl +
      "      <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>" + nl +
      "    </license>" + nl +
      "    <license>" + nl +
      "      <name>MIT License</name>" + nl +
      "      <url>https://opensource.org/licenses/MIT</url>" + nl +
      "    </license>" + nl +
      "    <license>" + nl +
      "      <name>The New BSD License</name>" + nl +
      "      <url>http://www.opensource.org/licenses/bsd-license.php</url>" + nl +
      "      <distribution>repo</distribution>" + nl +
      "      <comments>The 2-Clause BSD License</comments>" + nl +
      "    </license>" + nl
    );
  }
  @Test public void license_single() {
    poml(
      "license=$wtfpl" + nl +
      "$wtfpl=name>WTFPL, url>http://www.wtfpl.net/" + nl
    );
    More.license(poml, xml);
    xml(
      "    <license>" + nl +
      "      <name>WTFPL</name>" + nl +
      "      <url>http://www.wtfpl.net/</url>" + nl +
      "    </license>" + nl
    );
  }

  //-> developer
  @Test public void developer_multi() {
    poml(
      "developer=$jdoe, $ken" + nl +
      "$jdoe=" + nl +
      "  id>jdoe, name>John Doe," + nl +
      "  email>jdoe@example.com," + nl +
      "  url>http://www.example.com/jdoe" + nl +
      "$ken=id>ken" + nl
    );
    More.developer(poml, xml);
    xml(
      "    <developer>" + nl +
      "      <id>jdoe</id>" + nl +
      "      <name>John Doe</name>" + nl +
      "      <email>jdoe@example.com</email>" + nl +
      "      <url>http://www.example.com/jdoe</url>" + nl +
      "    </developer>" + nl +
      "    <developer>" + nl +
      "      <id>ken</id>" + nl +
      "    </developer>" + nl
    );
  }
  @Test public void developer_single() {
    poml(
      "developer=$ken" + nl +
      "$ken=id>ken" + nl
    );
    More.developer(poml, xml);
    xml(
      "    <developer>" + nl +
      "      <id>ken</id>" + nl +
      "    </developer>" + nl
    );
  }
}