package poml.conv.basic;

import static org.junit.Assert.fail;

import org.junit.Test;

import poml.Poml;
import poml.conv.ConvTestCase;
import poml.convert.Basic;

public class DependTest extends ConvTestCase {

  @Test public void id2art() {
    poml = Poml.parse(data(
      "depend=group.com:artifact"
    ));
    Basic.depend(poml, xml);
    result(
      "    <dependency>" + nl +
      "      <groupId>group.com</groupId>" + nl +
      "      <artifactId>artifact</artifactId>" + nl +
      "    </dependency>" + nl
    );
  }

  @Test public void id2ver() {
    poml = Poml.parse(data(
      "depend=group.com:artifact:0.0.1"
    ));
    Basic.depend(poml, xml);
    result(
      "    <dependency>" + nl +
      "      <groupId>group.com</groupId>" + nl +
      "      <artifactId>artifact</artifactId>" + nl +
      "      <version>0.0.1</version>" + nl +
      "    </dependency>" + nl
    );
  }

  @Test public void id2ver_type() {
    poml = Poml.parse(data(
      "depend=group.com:artifact:0.0.1:::jar"
    ));
    Basic.depend(poml, xml);
    result(
      "    <dependency>" + nl +
      "      <groupId>group.com</groupId>" + nl +
      "      <artifactId>artifact</artifactId>" + nl +
      "      <version>0.0.1</version>" + nl +
      "      <type>jar</type>" + nl +
      "    </dependency>" + nl
    );
  }

  @Test public void id2ver_opt() {
    poml = Poml.parse(data(
      "depend=group.com:artifact:0.0.1::false"
    ));
    Basic.depend(poml, xml);
    result(
      "    <dependency>" + nl +
      "      <groupId>group.com</groupId>" + nl +
      "      <artifactId>artifact</artifactId>" + nl +
      "      <version>0.0.1</version>" + nl +
      "      <optional>false</optional>" + nl +
      "    </dependency>" + nl
    );
  }

  @Test public void id2type() {
    poml = Poml.parse(data(
      "depend=group.com:artifact:0.0.1:test:true:jar"
    ));
    Basic.depend(poml, xml);
    result(
      "    <dependency>" + nl +
      "      <groupId>group.com</groupId>" + nl +
      "      <artifactId>artifact</artifactId>" + nl +
      "      <version>0.0.1</version>" + nl +
      "      <scope>test</scope>" + nl +
      "      <optional>true</optional>" + nl +
      "      <type>jar</type>" + nl +
      "    </dependency>" + nl
    );
  }

  @Test public void multi() {
    poml = Poml.parse(data(
      "depend=" + nl +
      "  demo.com:demo2," + nl +
      "  demo.com:demo:[4.12\\,)," + nl +
      "  sample.com:sample:0.0.1:provided," + nl +
      "  group.com:artifact:0.0.1:test:true:jar"
    ));
    Basic.depend(poml, xml);
    result(
      "    <dependency>" + nl +
      "      <groupId>demo.com</groupId>" + nl +
      "      <artifactId>demo2</artifactId>" + nl +
      "    </dependency>" + nl +
      "    <dependency>" + nl +
      "      <groupId>demo.com</groupId>" + nl +
      "      <artifactId>demo</artifactId>" + nl +
      "      <version>[4.12,)</version>" + nl +
      "    </dependency>" + nl +
      "    <dependency>" + nl +
      "      <groupId>sample.com</groupId>" + nl +
      "      <artifactId>sample</artifactId>" + nl +
      "      <version>0.0.1</version>" + nl +
      "      <scope>provided</scope>" + nl +
      "    </dependency>" + nl +
      "    <dependency>" + nl +
      "      <groupId>group.com</groupId>" + nl +
      "      <artifactId>artifact</artifactId>" + nl +
      "      <version>0.0.1</version>" + nl +
      "      <scope>test</scope>" + nl +
      "      <optional>true</optional>" + nl +
      "      <type>jar</type>" + nl +
      "    </dependency>" + nl
    );
  }

  @Test public void ng_noConf() {
    poml = Poml.parse(data(""));
    try {
      Basic.depend(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Config not found");
    }
  }

  @Test public void ng_emptyConf() {
    poml = Poml.parse(data("depend="));
    try {
      Basic.depend(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Config not found");
    }
  }

  @Test public void ng_badConf() {
    poml = Poml.parse(data(
      "depend=group.com:"
    ));
    try {
      Basic.depend(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config");
    }
  }

  @Test public void ng_badConf2() {
    poml = Poml.parse(data(
      "depend=" + nl +
      "  group.com:artifact:1.0," + nl +
      "  group.com:"
    ));
    try {
      Basic.depend(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config");
    }
  }

  @Test public void ng_badConf3() {
    poml = Poml.parse(data(
      "depend=, ," + nl +
      "  group.com:artifact:1.0"
    ));
    try {
      Basic.depend(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config");
    }
  }
}
