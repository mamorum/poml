package poml.converter.basic;

import org.junit.Test;

import poml.ConverterCase;

public class DependsTest extends ConverterCase {

  Depends conveter = new Depends();

  @Test public void id2type() {
    poml.conf.p.put("depends", "group.com:artifact:0.0.1:test:true:jar");
    conveter.convert(poml, pom);
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
    poml.conf.p.put("depends",
      "  demo.com:demo:0.0.1," +
      "  sample.com:sample:0.0.1:provided," +
      "  group.com:artifact:0.0.1:test:true:jar");
    conveter.convert(poml, pom);
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
