package poml.conv.build.plugin;

import java.util.Map;

import poml.io.Poml;
import poml.io.Xml;
import poml.tool.Throw;

public class Fatjar {
  private static final String name = "&fatjar";

  public void convert(Poml poml, Xml xml) {
    Map<String, String> map = poml.conf.map(name, false);
    String main = map.get("mainClass");  // required
    if (main == null) Throw.noKv(name, "mainClass");
    String ver = map.get("ver");
    if (ver == null) ver = "2.6";
    String jar = map.get("jarName");
    if (jar == null) jar = "${project.artifactId}";
    render(main, ver, jar, poml, xml);    
  }
  private void render(
    String main, String ver, String jar,
    Poml poml, Xml xml
  ) {
    xml.out
      .add("      <plugin>").nl()
      .add("        <groupId>org.apache.maven.plugins</groupId>").nl()
      .add("        <artifactId>maven-assembly-plugin</artifactId>").nl()
      .add("        <version>").add(ver).add("</version>").nl()
      .add("        <configuration>").nl()
      .add("          <finalName>").add(jar).add("</finalName>").nl()
      .add("          <descriptorRefs>").nl()
      .add("            <descriptorRef>jar-with-dependencies</descriptorRef>").nl()
      .add("          </descriptorRefs>").nl()
      .add("          <appendAssemblyId>false</appendAssemblyId>").nl()
      .add("          <attach>false</attach>").nl()
      .add("          <archive>").nl()
      .add("            <manifest>").nl()
      .add("              <mainClass>").add(main).add("</mainClass>").nl()
      .add("            </manifest>").nl()
      .add(
          tags("&fatjar.conf.archive+", "          ", poml)
       )
      .add("          </archive>").nl()
      .add(
          tags("&fatjar.conf+", "        ", poml)
       )
      .add("        </configuration>").nl()
      .add("        <executions>").nl()
      .add("          <execution>").nl()
      .add("            <id>make-assembly</id>").nl()
      .add("            <phase>package</phase>").nl()
      .add("            <goals><goal>single</goal></goals>").nl()
      .add("          </execution>").nl()
      .add("        </executions>").nl()
      .add("      </plugin>").nl();
  }
  private String tags(String key, String sp, Poml poml) {
    if (poml.conf.hasTag(key)) return poml.conf.tag(key, sp);
    else return "";
  }
}
