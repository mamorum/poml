package poml.conv.build.plugin;

import poml.io.Xml;

// plugins: javadoc, source, gpg 
// http://central.sonatype.org/pages/apache-maven.html
public class Dsg {
  public void add(Xml xml) {
    xml.out
      .add("      <plugin>").nl()
      .add("        <groupId>org.apache.maven.plugins</groupId>").nl()
      .add("        <artifactId>maven-source-plugin</artifactId>").nl()
      .add("        <version>2.2.1</version>").nl()
      .add("        <executions>").nl()
      .add("          <execution>").nl()
      .add("            <id>attach-sources</id>").nl()
      .add("            <goals><goal>jar-no-fork</goal></goals>").nl()
      .add("          </execution>").nl()
      .add("        </executions>").nl()
      .add("      </plugin>").nl()
      .add("      <plugin>").nl()
      .add("        <groupId>org.apache.maven.plugins</groupId>").nl()
      .add("        <artifactId>maven-javadoc-plugin</artifactId>").nl()
      .add("        <version>2.9.1</version>").nl()
      .add("        <executions>").nl()
      .add("          <execution>").nl()
      .add("            <id>attach-javadocs</id>").nl()
      .add("            <goals><goal>jar</goal></goals>").nl()
      .add("          </execution>").nl()
      .add("        </executions>").nl()
      .add("      </plugin>").nl()
      .add("      <plugin>").nl()
      .add("        <groupId>org.apache.maven.plugins</groupId>").nl()
      .add("        <artifactId>maven-gpg-plugin</artifactId>").nl()
      .add("        <version>1.5</version>").nl()
      .add("        <executions>").nl()
      .add("          <execution>").nl()
      .add("            <id>sign-artifacts</id>").nl()
      .add("            <phase>verify</phase>").nl()
      .add("            <goals><goal>sign</goal></goals>").nl()
      .add("          </execution>").nl()
      .add("        </executions>").nl()
      .add("      </plugin>").nl();
  }
}