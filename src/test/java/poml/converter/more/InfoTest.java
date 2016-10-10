package poml.converter.more;

import org.junit.Test;

import poml.ConverterCase;

public class InfoTest extends ConverterCase {

  Info conveter = new Info();
  
  @Test public void name() {
    poml.conf.p.put("info", "name:INFO");
    conveter.convert(poml, xml);
    output.is(
      "  <name>INFO</name>" + nl
    );
  }
  
  @Test public void name_license_mit() {
    poml.conf.p.put("info",
      "  name:INFO, " +
      "  description:More Project Infomation, " +
      "  url:https://github.com/mamorum/poml, " +
      "  inceptionYear:2016, " +
      "  license:MIT ");
    conveter.convert(poml, xml);
    output.is(
      "  <name>INFO</name>" + nl + 
      "  <description>More Project Infomation</description>" + nl +
      "  <url>https://github.com/mamorum/poml</url>" + nl +
      "  <inceptionYear>2016</inceptionYear>" + nl +
      "  <licenses>" + nl +
      "    <license>" + nl +
      "      <name>MIT License</name>" + nl +
      "      <url>http://www.opensource.org/licenses/mit-license.php</url>" + nl +
      "    </license>" + nl +
      "  </licenses>" + nl
    );
  }
  
  @Test public void license_apache() {
    poml.conf.p.put("info", "license:Apache 2.0");
    conveter.convert(poml, xml);
    output.is(
      "  <licenses>" + nl +
      "    <license>" + nl +
      "      <name>The Apache License, Version 2.0</name>" + nl +
      "      <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>" + nl +
      "    </license>" + nl +
      "  </licenses>" + nl
    );
  }
}
