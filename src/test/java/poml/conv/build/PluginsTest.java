package poml.conv.build;

import org.junit.Test;

import poml.conv.ConvTestCase;

public class PluginsTest extends ConvTestCase {

  Plugins conv = new Plugins();

  @Test public void defaults() {
    poml.conf.append("build.plugins=$sbp");
    poml.conf.append("$sbp=org.springframework.boot:spring-boot-maven-plugin");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "    <plugins>" + nl +
      "      <plugin>" + nl +
      "        <groupId>org.springframework.boot</groupId>" + nl +
      "        <artifactId>spring-boot-maven-plugin</artifactId>" + nl +
      "      </plugin>" + nl +
      "    </plugins>" + nl
    );
  }
}
