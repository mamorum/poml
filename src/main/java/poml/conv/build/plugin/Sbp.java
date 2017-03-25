package poml.conv.build.plugin;

import poml.conv.Converter;
import poml.io.Poml;
import poml.io.Xml;

// for Spring Boot Plugin
public class Sbp implements Converter {

  @Override public String name() { return "sbp"; }

  @Override public void convert(Poml poml, Xml xml) {
    xml.out.add(tags);
  }
  private static final String[] tags = {
    "      <plugin>",
    "        <groupId>org.springframework.boot</groupId>",
    "        <artifactId>spring-boot-maven-plugin</artifactId>",
    "      </plugin>"
  };
}
