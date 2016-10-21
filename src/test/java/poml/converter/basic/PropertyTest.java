package poml.converter.basic;

import org.junit.Test;

import poml.ConverterCase;

public class PropertyTest extends ConverterCase {

  Property conveter = new Property();
  
  @Test public void single() {
    poml.conf.append("property=project.build.sourceEncoding:UTF-8");
    poml.conf.load();
    conveter.convert(poml, xml);
    output.is(
      "  <properties>" + nl + 
      "    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>" + nl +
      "  </properties>" + nl
    );
  }
  
  @Test public void multi() {
    poml.conf.append("property=" +
        "  property:value," +
        "  project.build.sourceEncoding:UTF-8");
    poml.conf.load();
    conveter.convert(poml, xml);
    output.is(
        "  <properties>" + nl + 
        "    <property>value</property>" + nl +
        "    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>" + nl +
        "  </properties>" + nl
    );
  }

}
