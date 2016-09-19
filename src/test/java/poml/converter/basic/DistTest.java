package poml.converter.basic;

import org.junit.Test;

import poml.ConverterCase;

public class DistTest extends ConverterCase {

  Dist conveter = new Dist();
  
  @Test public void id_ver() {
    src.prop.put("dist", "group.com:artifact:0.0.1");
    conveter.convert(src, dst);
    output.is(
      "  <groupId>group.com</groupId>" + nl + 
      "  <artifactId>artifact</artifactId>" + nl +
      "  <version>0.0.1</version>" + nl
    );
  }
  
  @Test public void id_ver_pkg() {
    src.prop.put("dist", "group.com:artifact:0.0.1:jar");
    conveter.convert(src, dst);
    output.is(
      "  <groupId>group.com</groupId>" + nl + 
      "  <artifactId>artifact</artifactId>" + nl +
      "  <version>0.0.1</version>" + nl +
      "  <packaging>jar</packaging>" + nl
    );
  }

}
