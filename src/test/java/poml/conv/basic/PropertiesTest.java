package poml.conv.basic;

import static org.junit.Assert.fail;

import org.junit.Test;

import poml.conv.ConvTestCase;
import poml.in.Poml;

public class PropertiesTest extends ConvTestCase {

  Properties conv = new Properties();

  @Test public void single() {
    poml = Poml.parse(data(
      "properties=project.build.sourceEncoding>UTF-8"
    ));
    conv.convert(poml, xml);
    result(
      "  <properties>" + nl +
      "    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>" + nl +
      "  </properties>" + nl
    );
  }

  @Test public void multi() {
    poml = Poml.parse(data(
      "properties=" + nl +
      "  property>value," + nl +
      "  project.build.sourceEncoding>UTF-8"
    ));
    conv.convert(poml, xml);
    result(
      "  <properties>" + nl +
      "    <property>value</property>" + nl +
      "    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>" + nl +
      "  </properties>" + nl
    );
  }

  @Test public void replaceEncoding() {
    poml = Poml.parse(data(
      "properties=&encoding>UTF-8"
    ));
    conv.convert(poml, xml);
    result(
      "  <properties>" + nl +
      "    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>" + nl +
      "    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>" + nl +
      "  </properties>" + nl
    );
  }
  @Test public void replaceCompiler() {
    poml = Poml.parse(data(
      "properties=&compiler>1.8"
    ));
    conv.convert(poml, xml);
    result(
      "  <properties>" + nl +
      "    <maven.compiler.source>1.8</maven.compiler.source>" + nl +
      "    <maven.compiler.target>1.8</maven.compiler.target>" + nl +
      "  </properties>" + nl
    );
  }

  @Test public void replaceMulti() {
    poml = Poml.parse(data(
      "properties=" + nl +
      "  property1>value1," + nl +
      "  &encoding>UTF-8," + nl +
      "  property2>value2," + nl +
      "  &compiler>1.8," + nl +
      "  property3>value3"
    ));
    conv.convert(poml, xml);
    result(
      "  <properties>" + nl +
      "    <property1>value1</property1>" + nl +
      "    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>" + nl +
      "    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>" + nl +
      "    <property2>value2</property2>" + nl +
      "    <maven.compiler.source>1.8</maven.compiler.source>" + nl +
      "    <maven.compiler.target>1.8</maven.compiler.target>" + nl +
      "    <property3>value3</property3>" + nl +
      "  </properties>" + nl
    );
  }

  @Test public void ng_noConf() {
    poml = Poml.parse(data(""));
    try {
      conv.convert(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Config not found");
    }
  }

  @Test public void ng_emptyConf() {
    poml = Poml.parse(data("properties="));
    try {
      conv.convert(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Config not found");
    }
  }

  @Test public void ng_badConf() {
    poml = Poml.parse(data("properties=:"));
    try {
      conv.convert(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config");
    }
  }

  @Test public void ng_badConf2() {
    poml = Poml.parse(data(
      "properties=" + nl +
      "  key:val," + nl +
      "  keyval"
    ));
    try {
      conv.convert(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config");
    }
  }
}
