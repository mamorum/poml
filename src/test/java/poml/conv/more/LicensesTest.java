package poml.conv.more;

import static org.junit.Assert.fail;

import org.junit.Test;

import poml.conv.ConvTestCase;

public class LicensesTest extends ConvTestCase {

  Licenses conv = new Licenses();
  
  @Test public void apache2() {
    poml.conf.append("licenses=&apache2");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "  <licenses>" + nl +
      "    <license>" + nl +
      "      <name>The Apache License, Version 2.0</name>" + nl +
      "      <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>" + nl +
      "    </license>" + nl +
      "  </licenses>" + nl
    );
  }
  
  @Test public void usr() {
    poml.conf.append("licenses=$bsd2");
    poml.conf.append("$bsd2=");
    poml.conf.append("  name: The New BSD License,");
    poml.conf.append("  url: http://www.opensource.org/licenses/bsd-license.php,");
    poml.conf.append("  distribution: repo,");
    poml.conf.append("  comments: The 2-Clause BSD License");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "  <licenses>" + nl +
      "    <license>" + nl +
      "      <name>The New BSD License</name>" + nl +
      "      <url>http://www.opensource.org/licenses/bsd-license.php</url>" + nl +
      "      <distribution>repo</distribution>" + nl +
      "      <comments>The 2-Clause BSD License</comments>" + nl +
      "    </license>" + nl +
      "  </licenses>" + nl
    );
  }
  
  @Test public void multi() {
    poml.conf.append("licenses=$wtfpl, &mit");
    poml.conf.append("$wtfpl=");
    poml.conf.append("  name: WTFPL,");
    poml.conf.append("  url: http://www.wtfpl.net/");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "  <licenses>" + nl +
      "    <license>" + nl +
      "      <name>WTFPL</name>" + nl +
      "      <url>http://www.wtfpl.net/</url>" + nl +
      "    </license>" + nl +
      "    <license>" + nl +
      "      <name>MIT License</name>" + nl +
      "      <url>https://opensource.org/licenses/MIT</url>" + nl +
      "    </license>" + nl +
      "  </licenses>" + nl
    );
  }

  @Test public void ng_badConf() {
    poml.conf.append("licenses=$ng");
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
