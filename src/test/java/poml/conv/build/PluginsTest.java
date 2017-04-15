package poml.conv.build;

import org.junit.Test;

import poml.conv.ConvTestCase;

public class PluginsTest extends ConvTestCase {

//  Plugins conv = new Plugins();

  // -> Test for Plugins.Plugin
  @Test public void grp_art() {
    Plugin conv = new Plugin("$sbp");
    poml.conf.parse(data(
      "$sbp=org.springframework.boot:spring-boot-maven-plugin"
    ));
    conv.convert(poml, xml);
    result(
      "      <plugin>" + nl +
      "        <groupId>org.springframework.boot</groupId>" + nl +
      "        <artifactId>spring-boot-maven-plugin</artifactId>" + nl +
      "      </plugin>" + nl
    );
  }
}
