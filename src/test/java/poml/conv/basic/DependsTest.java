package poml.conv.basic;

import static org.junit.Assert.fail;

import org.junit.Test;

import poml.conv.ConvTestCase;

public class DependsTest extends ConvTestCase {

  Depends conv = new Depends();

  @Test public void id2type() {
    poml.conf.parse(data(
      "depends=group.com:artifact:0.0.1:test:true:jar"
    ));
    conv.convert(poml, xml);
    result(
        "  <dependencies>" + nl +
        "    <dependency>" + nl +
        "      <groupId>group.com</groupId>" + nl + 
        "      <artifactId>artifact</artifactId>" + nl +
        "      <version>0.0.1</version>" + nl +
        "      <scope>test</scope>" + nl +
        "      <optional>true</optional>" + nl +
        "      <type>jar</type>" + nl +
        "    </dependency>" + nl +
        "  </dependencies>" + nl
    );
  }

  @Test public void multi() {
    poml.conf.parse(data(
      "depends=" + nl +
      "  demo.com:demo2," + nl +
      "  demo.com:demo:0.0.1," + nl +
      "  sample.com:sample:0.0.1:provided," + nl +
      "  group.com:artifact:0.0.1:test:true:jar" + nl
    ));
    conv.convert(poml, xml);
    result(
      "  <dependencies>" + nl +
      "    <dependency>" + nl +
      "      <groupId>demo.com</groupId>" + nl + 
      "      <artifactId>demo2</artifactId>" + nl +
      "    </dependency>" + nl +
      "    <dependency>" + nl +
      "      <groupId>demo.com</groupId>" + nl + 
      "      <artifactId>demo</artifactId>" + nl +
      "      <version>0.0.1</version>" + nl +
      "    </dependency>" + nl +
      "    <dependency>" + nl +
      "      <groupId>sample.com</groupId>" + nl + 
      "      <artifactId>sample</artifactId>" + nl +
      "      <version>0.0.1</version>" + nl +
      "      <scope>provided</scope>" + nl +
      "    </dependency>" + nl +
      "    <dependency>" + nl +
      "      <groupId>group.com</groupId>" + nl + 
      "      <artifactId>artifact</artifactId>" + nl +
      "      <version>0.0.1</version>" + nl +
      "      <scope>test</scope>" + nl +
      "      <optional>true</optional>" + nl +
      "      <type>jar</type>" + nl +
      "    </dependency>" + nl +
      "  </dependencies>" + nl
    );
  }

  @Test public void ng_noConf() {
    poml.conf.parse(data(""));
    try { 
      conv.convert(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Config not found");
    }
  }
  
  @Test public void ng_badConf() {
    poml.conf.parse(data(
      "depends=group.com:"
    ));
    try { 
      conv.convert(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config");
    }
  }
}
