package poml.conv.build.plugin;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;

// for Spring Boot Plugin
public class Sbp implements Converter {

  @Override public String name() { return "sbp"; }

  @Override public void convert(Poml poml, Xml xml) {
    xml.print(tag);
  }
  private static final String[] tag = {
    "      <plugin>",
    "        <groupId>org.springframework.boot</groupId>",
    "        <artifactId>spring-boot-maven-plugin</artifactId>",
    "      </plugin>"
  };
}
