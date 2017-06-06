package poml.convert;

import static org.junit.Assert.fail;

import org.junit.Test;

import poml.UtCase;

public class MoreLicenseTest extends UtCase {

  @Test public void apache2() {
    poml(
      "license=&apache2"
    );
    More.license(poml, xml);
    xml(
      "    <license>" + nl +
      "      <name>The Apache License, Version 2.0</name>" + nl +
      "      <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>" + nl +
      "    </license>" + nl
    );
  }

  @Test public void usr() {
    poml(
      "license=$bsd2" + nl +
      "$bsd2=" + nl +
      "  name>The New BSD License," + nl +
      "  url>http://www.opensource.org/licenses/bsd-license.php," + nl +
      "  distribution>repo," + nl +
      "  comments>The 2-Clause BSD License" + nl
    );
    More.license(poml, xml);
    xml(
      "    <license>" + nl +
      "      <name>The New BSD License</name>" + nl +
      "      <url>http://www.opensource.org/licenses/bsd-license.php</url>" + nl +
      "      <distribution>repo</distribution>" + nl +
      "      <comments>The 2-Clause BSD License</comments>" + nl +
      "    </license>" + nl
    );
  }

  @Test public void multi() {
    poml(
      "license=$wtfpl, &mit" + nl +
      "$wtfpl=" + nl +
      "  name>WTFPL," + nl +
      "  url>http://www.wtfpl.net/" + nl
    );
    More.license(poml, xml);
    xml(
      "    <license>" + nl +
      "      <name>WTFPL</name>" + nl +
      "      <url>http://www.wtfpl.net/</url>" + nl +
      "    </license>" + nl +
      "    <license>" + nl +
      "      <name>MIT License</name>" + nl +
      "      <url>https://opensource.org/licenses/MIT</url>" + nl +
      "    </license>" + nl
    );
  }

  @Test public void ng_badConf() {
    poml(
      "license=$ng" + nl +
      "$ng=bad" + nl
    );
    try {
      More.license(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      err("Invalid config [key=$ng] [val=bad]", e);
    }
  }
}
