package poml.conv.more;

import static org.junit.Assert.fail;

import org.junit.Test;

import poml.conv.ConvTestCase;
import poml.convert.More;

public class DeveloperTest extends ConvTestCase {

  @Test public void single() {
    poml(
      "developer=$jdoe" + nl +
      "$jdoe=" + nl +
      "  id>jdoe, name>John Doe," + nl +
      "  email>jdoe@example.com," + nl +
      "  url>http://www.example.com/jdoe" + nl
    );
    More.developer(poml, xml);
    result(
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
    result(
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
      msg(e).starts("Bad config");
    }
  }
}
