package poml.converter.basic;

import org.junit.Test;

import poml.ConverterCase;

public class PropTest extends ConverterCase {

  Property conveter = new Property();
  
  @Test public void single() {
    src.prop.put("property", "project.build.sourceEncoding:UTF-8");
    conveter.convert(src, dst);
    output.is(
      "  <properties>" + nl + 
      "    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>" + nl +
      "  </properties>" + nl
    );
  }
  
  @Test public void multi() {
    src.prop.put("property",
        "  property:value," +
        "  project.build.sourceEncoding:UTF-8");
    conveter.convert(src, dst);
    output.is(
        "  <properties>" + nl + 
        "    <property>value</property>" + nl +
        "    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>" + nl +
        "  </properties>" + nl
    );
  }

}
