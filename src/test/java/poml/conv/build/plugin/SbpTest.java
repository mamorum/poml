package poml.conv.build.plugin;

import org.junit.Test;

import poml.conv.ConvTestCase;

public class SbpTest extends ConvTestCase {

  Sbp conv = new Sbp();

  @Test public void defaults() {
    poml.conf.append("sbp=_default");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "      <plugin>" + nl +
      "        <groupId>org.springframework.boot</groupId>" + nl +
      "        <artifactId>spring-boot-maven-plugin</artifactId>" + nl +
      "      </plugin>" + nl
    );
  }
}
