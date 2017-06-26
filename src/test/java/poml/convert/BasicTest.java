package poml.convert;

import org.junit.Test;

import poml.UtCase;

public class BasicTest extends UtCase {

  //-> pkg
  @Test public void pkg_id2pac() {
    poml(
      "pkg=com.example:demo:0.0.1:jar"
    );
    Basic.pkg(poml, xml);
    xml(
      "  <groupId>com.example</groupId>" + nl +
      "  <artifactId>demo</artifactId>" + nl +
      "  <version>0.0.1</version>" + nl +
      "  <packaging>jar</packaging>" + nl
    );
  }
  @Test public void pkg_id2ver() {
    poml(
      "pkg=com.example:demo:0.0.1"
    );
    Basic.pkg(poml, xml);
    xml(
      "  <groupId>com.example</groupId>" + nl +
      "  <artifactId>demo</artifactId>" + nl +
      "  <version>0.0.1</version>" + nl
    );
  }

  //-> parent
  @Test public void parent_id2ver() {
    poml(
      "parent=com.example:demo-parent:0.0.1"
    );
    Basic.parent(poml, xml);
    xml(
      "  <parent>" + nl +
      "    <groupId>com.example</groupId>" + nl +
      "    <artifactId>demo-parent</artifactId>" + nl +
      "    <version>0.0.1</version>" + nl +
      "  </parent>" + nl
    );
  }
  @Test public void parent_id2rel() {
    poml(
      "parent=com.example:demo-parent:0.0.1:../pom.xml"
    );
    Basic.parent(poml, xml);
    xml(
      "  <parent>" + nl +
      "    <groupId>com.example</groupId>" + nl +
      "    <artifactId>demo-parent</artifactId>" + nl +
      "    <version>0.0.1</version>" + nl +
      "    <relativePath>../pom.xml</relativePath>" + nl +
      "  </parent>" + nl
    );
  }

  //-> depend
  @Test public void depend_multi() {
    poml(
      "depend=" + nl +
      "  com.example:artifact1:0.0.1:test:true:jar," + nl +
      "  com.example:artifact2::::jar," + nl +
      "  com.example:artifact3"
    );
    Basic.depend(poml, xml);
    xml(
      "    <dependency>" + nl +
      "      <groupId>com.example</groupId>" + nl +
      "      <artifactId>artifact1</artifactId>" + nl +
      "      <version>0.0.1</version>" + nl +
      "      <scope>test</scope>" + nl +
      "      <optional>true</optional>" + nl +
      "      <type>jar</type>" + nl +
      "    </dependency>" + nl +
      "    <dependency>" + nl +
      "      <groupId>com.example</groupId>" + nl +
      "      <artifactId>artifact2</artifactId>" + nl +
      "      <type>jar</type>" + nl +
      "    </dependency>" + nl +
      "    <dependency>" + nl +
      "      <groupId>com.example</groupId>" + nl +
      "      <artifactId>artifact3</artifactId>" + nl +
      "    </dependency>" + nl
    );
  }
  @Test public void depend_single() {
    poml(
      "depend=junit:junit:[4.12\\,):test"
    );
    Basic.depend(poml, xml);
    xml(
      "    <dependency>" + nl +
      "      <groupId>junit</groupId>" + nl +
      "      <artifactId>junit</artifactId>" + nl +
      "      <version>[4.12,)</version>" + nl +
      "      <scope>test</scope>" + nl +
      "    </dependency>" + nl
    );
  }

  //-> properties

  @Test public void properties_multi() {
    poml(
      "properties=" + nl +
      "  gpg.skip>true," + nl +
      "  &encoding>UTF-8, &compiler>1.8"
    );
    Basic.properties(poml, xml);
    xml(
        "  <properties>" + nl +
        "    <gpg.skip>true</gpg.skip>" + nl +
        "    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>" + nl +
        "    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>" + nl +
        "    <maven.compiler.source>1.8</maven.compiler.source>" + nl +
        "    <maven.compiler.target>1.8</maven.compiler.target>" + nl +
        "  </properties>" + nl
    );
  }

  @Test public void properties_single() {
    poml(
      "properties=gpg.skip>true"
    );
    Basic.properties(poml, xml);
    xml(
      "  <properties>" + nl +
      "    <gpg.skip>true</gpg.skip>" + nl +
      "  </properties>" + nl
    );
  }
}
