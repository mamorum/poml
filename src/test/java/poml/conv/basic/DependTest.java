package poml.conv.basic;

import static org.junit.Assert.fail;

import org.junit.Test;

import poml.conv.ConvTestCase;
import poml.in.Poml;

public class DependTest extends ConvTestCase {

  Depend conv = new Depend();

  @Test public void id2art() {
    poml = Poml.parse(data(
      "depend=group.com:artifact"
    ));
    conv.convert(poml, xml);
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
    conv.convert(poml, xml);
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
    conv.convert(poml, xml);
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
    conv.convert(poml, xml);
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
    conv.convert(poml, xml);
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
    conv.convert(poml, xml);
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
      conv.convert(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Config not found");
    }
  }

  @Test public void ng_emptyConf() {
    poml = Poml.parse(data("depend="));
    try {
      conv.convert(poml, xml);
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
      conv.convert(poml, xml);
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
      conv.convert(poml, xml);
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
      conv.convert(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config");
    }
  }
}
