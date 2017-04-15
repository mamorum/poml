package poml.conv.more;

import static org.junit.Assert.fail;

import org.junit.Test;

import poml.conv.ConvTestCase;

public class DevelopersTest extends ConvTestCase {

  Developers conv = new Developers();
  
  @Test public void single() {
    poml.conf.parse(data(
      "developers=$jdoe" + nl +
      "$jdoe=" + nl +
      "  id: jdoe, name: John Doe," + nl +
      "  email: jdoe@example.com," + nl +
      "  url: http://www.example.com/jdoe" + nl
    ));
    conv.convert(poml, xml);
    result(
      "  <developers>" + nl +
      "    <developer>" + nl +
      "      <id>jdoe</id>" + nl +
      "      <name>John Doe</name>" + nl +
      "      <email>jdoe@example.com</email>" + nl +
      "      <url>http://www.example.com/jdoe</url>" + nl +
      "    </developer>" + nl +
      "  </developers>" + nl
    );
  }
  
  @Test public void multi() {
    poml.conf.parse(data(
      "developers=$jdoe, $ken" + nl +
      "$jdoe=" + nl +
      "  id: jdoe, name: John Doe," + nl +
      "  email: jdoe@example.com," + nl +
      "  url: http://www.example.com/jdoe" + nl +
      "$ken=id: ken" + nl
    ));
    conv.convert(poml, xml);
    result(
      "  <developers>" + nl +
      "    <developer>" + nl +
      "      <id>jdoe</id>" + nl +
      "      <name>John Doe</name>" + nl +
      "      <email>jdoe@example.com</email>" + nl +
      "      <url>http://www.example.com/jdoe</url>" + nl +
      "    </developer>" + nl +
      "    <developer>" + nl +
      "      <id>ken</id>" + nl +
      "    </developer>" + nl +
      "  </developers>" + nl
    );
  }

  @Test public void ng_badConf() {
    poml.conf.parse(data(
      "developers=$ng" + nl +
      "$ng=bad" + nl
    ));
    try { 
      conv.convert(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config");
    }
  }
}
