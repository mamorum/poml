package poml.convert;

import static org.junit.Assert.fail;

import org.junit.Test;

public class MoreDeveloperTest extends TestCase {

  @Test public void single() {
    poml(
      "developer=$jdoe" + nl +
      "$jdoe=" + nl +
      "  id>jdoe, name>John Doe," + nl +
      "  email>jdoe@example.com," + nl +
      "  url>http://www.example.com/jdoe" + nl
    );
    More.developer(poml, xml);
    xml(
      "    <developer>" + nl +
      "      <id>jdoe</id>" + nl +
      "      <name>John Doe</name>" + nl +
      "      <email>jdoe@example.com</email>" + nl +
      "      <url>http://www.example.com/jdoe</url>" + nl +
      "    </developer>" + nl
    );
  }

  @Test public void multi() {
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

  @Test public void ng_badConf() {
    poml(
      "developer=$ng" + nl +
      "$ng=bad" + nl
    );
    try {
      More.developer(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      err("Invalid config [key=$ng] [val=bad]", e);
    }
  }
}
