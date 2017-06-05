package poml.convert;

import static org.junit.Assert.fail;

import org.junit.Test;

public class BasicDependTest extends TestCase {

  @Test public void id2art() {
    poml(
      "depend=group.com:artifact"
    );
    Basic.depend(poml, xml);
    xml(
      "    <dependency>" + nl +
      "      <groupId>group.com</groupId>" + nl +
      "      <artifactId>artifact</artifactId>" + nl +
      "    </dependency>" + nl
    );
  }

  @Test public void id2ver() {
    poml(
      "depend=group.com:artifact:0.0.1"
    );
    Basic.depend(poml, xml);
    xml(
      "    <dependency>" + nl +
      "      <groupId>group.com</groupId>" + nl +
      "      <artifactId>artifact</artifactId>" + nl +
      "      <version>0.0.1</version>" + nl +
      "    </dependency>" + nl
    );
  }

  @Test public void id2ver_type() {
    poml(
      "depend=group.com:artifact:0.0.1:::jar"
    );
    Basic.depend(poml, xml);
    xml(
      "    <dependency>" + nl +
      "      <groupId>group.com</groupId>" + nl +
      "      <artifactId>artifact</artifactId>" + nl +
      "      <version>0.0.1</version>" + nl +
      "      <type>jar</type>" + nl +
      "    </dependency>" + nl
    );
  }

  @Test public void id2ver_opt() {
    poml(
      "depend=group.com:artifact:0.0.1::false"
    );
    Basic.depend(poml, xml);
    xml(
      "    <dependency>" + nl +
      "      <groupId>group.com</groupId>" + nl +
      "      <artifactId>artifact</artifactId>" + nl +
      "      <version>0.0.1</version>" + nl +
      "      <optional>false</optional>" + nl +
      "    </dependency>" + nl
    );
  }

  @Test public void id2type() {
    poml(
      "depend=group.com:artifact:0.0.1:test:true:jar"
    );
    Basic.depend(poml, xml);
    xml(
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
    poml(
      "depend=" + nl +
      "  demo.com:demo2," + nl +
      "  demo.com:demo:[4.12\\,)," + nl +
      "  sample.com:sample:0.0.1:provided," + nl +
      "  group.com:artifact:0.0.1:test:true:jar"
    );
    Basic.depend(poml, xml);
    xml(
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
    poml("");
    try {
      Basic.depend(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      err("Invalid config [key=depend] [val=null]", e);
    }
  }

  @Test public void ng_emptyConf() {
    poml("depend=");
    try {
      Basic.depend(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      err("Invalid config [key=depend] [val=]", e);
    }
  }

  @Test public void ng_val() {
    poml(
      "depend=group.com:"
    );
    try {
      Basic.depend(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      err("Invalid config [key=depend] [val=group.com:]", e);
    }
  }

  @Test public void ng_val2() {
    poml(
      "depend=" + nl +
      "  group.com:artifact:1.0," + nl +
      "  group.com:"
    );
    try {
      Basic.depend(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      err("Invalid config [key=depend] [val=group.com:]", e);
    }
  }

  @Test public void ng_badConf3() {
    poml(
      "depend=, ," + nl +
      "  group.com:artifact:1.0"
    );
    try {
      Basic.depend(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      err(
        "Invalid config [key=depend] [val=, ,  group.com:artifact:1.0]",
        e
      );
    }
  }
}
