package poml.conv.more;

import static org.junit.Assert.fail;

import org.junit.Test;

import poml.conv.ConvTestCase;

public class DevelopersTest extends ConvTestCase {

  Developers conv = new Developers();
  
  @Test public void single() {
    poml.conf.append("developers=$jdoe");
    poml.conf.append("$jdoe=");
    poml.conf.append("  id: jdoe, name: John Doe,");
    poml.conf.append("  email: jdoe@example.com,");
    poml.conf.append("  url: http://www.example.com/jdoe");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
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
    poml.conf.append("developers=$jdoe, $ken");
    poml.conf.append("$jdoe=");
    poml.conf.append("  id: jdoe, name: John Doe,");
    poml.conf.append("  email: jdoe@example.com,");
    poml.conf.append("  url: http://www.example.com/jdoe");
    poml.conf.append("$ken=id: ken");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
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
    poml.conf.append("developers=$ng");
    poml.conf.append("$ng=bad");
    poml.conf.load();
    try { 
      conv.convert(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config");
    }
  }
}
