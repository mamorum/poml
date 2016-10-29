package poml.conv.basic;

import org.junit.Test;

import poml.conv.ConvTestCase;
import poml.conv.basic.Depends;

public class DependsTest extends ConvTestCase {

  Depends conv = new Depends();

  @Test public void id2type() {
    poml.conf.append("depends=group.com:artifact:0.0.1:test:true:jar");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
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
    poml.conf.append("depends=");
    poml.conf.append("  demo.com:demo:0.0.1,");
    poml.conf.append("  sample.com:sample:0.0.1:provided,");
    poml.conf.append("  group.com:artifact:0.0.1:test:true:jar");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "  <dependencies>" + nl +
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
}
