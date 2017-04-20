package poml.conv.more;

import static org.junit.Assert.fail;

import org.junit.Test;

import poml.conv.ConvTestCase;

public class LicenseTest extends ConvTestCase {

  License conv = new License();
  
  @Test public void apache2() {
    poml.conf.parse(data(
      "license=&apache2"
    ));
    conv.convert(poml, xml);
    result(
      "    <license>" + nl +
      "      <name>The Apache License, Version 2.0</name>" + nl +
      "      <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>" + nl +
      "    </license>" + nl
    );
  }
  
  @Test public void usr() {
    poml.conf.parse(data(
      "license=$bsd2" + nl +
      "$bsd2=" + nl +
      "  name>The New BSD License," + nl +
      "  url>http://www.opensource.org/licenses/bsd-license.php," + nl +
      "  distribution>repo," + nl +
      "  comments>The 2-Clause BSD License" + nl
    ));
    conv.convert(poml, xml);
    result(
      "    <license>" + nl +
      "      <name>The New BSD License</name>" + nl +
      "      <url>http://www.opensource.org/licenses/bsd-license.php</url>" + nl +
      "      <distribution>repo</distribution>" + nl +
      "      <comments>The 2-Clause BSD License</comments>" + nl +
      "    </license>" + nl
    );
  }
  
  @Test public void multi() {
    poml.conf.parse(data(
      "license=$wtfpl, &mit" + nl +
      "$wtfpl=" + nl +
      "  name>WTFPL," + nl +
      "  url>http://www.wtfpl.net/" + nl
    ));
    conv.convert(poml, xml);
    result(
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
    poml.conf.parse(data(
      "license=$ng" + nl +
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
