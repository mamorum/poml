package poml.conv.basic;

import org.junit.Test;

import poml.conv.ConvTestCase;
import poml.conv.basic.Property;

public class PropertyTest extends ConvTestCase {

  Property conv = new Property();
  
  @Test public void single() {
    poml.conf.append("property=project.build.sourceEncoding:UTF-8");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "  <properties>" + nl + 
      "    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>" + nl +
      "  </properties>" + nl
    );
  }
  
  @Test public void multi() {
    poml.conf.append("property=");
    poml.conf.append("  property:value,");
    poml.conf.append("  project.build.sourceEncoding:UTF-8");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "  <properties>" + nl + 
      "    <property>value</property>" + nl +
      "    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>" + nl +
      "  </properties>" + nl
    );
  }
}
