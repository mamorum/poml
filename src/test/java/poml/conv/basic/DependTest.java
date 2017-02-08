package poml.conv.basic;

import static org.junit.Assert.fail;

import org.junit.Test;

import poml.conv.ConvTestCase;

public class DependTest extends ConvTestCase {

  Depend conv = new Depend();
  
  @Test public void id2art() {
    poml.conf.append("depend=group.com:artifact");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "    <dependency>" + nl +
      "      <groupId>group.com</groupId>" + nl + 
      "      <artifactId>artifact</artifactId>" + nl +
      "    </dependency>" + nl
    );
  }
  
  @Test public void id2ver() {
    poml.conf.append("depend=group.com:artifact:0.0.1");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "    <dependency>" + nl +
      "      <groupId>group.com</groupId>" + nl + 
      "      <artifactId>artifact</artifactId>" + nl +
      "      <version>0.0.1</version>" + nl +
      "    </dependency>" + nl
    );
  }

  @Test public void id2ver_type() {
    poml.conf.append("depend=group.com:artifact:0.0.1:::jar");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "    <dependency>" + nl +
      "      <groupId>group.com</groupId>" + nl + 
      "      <artifactId>artifact</artifactId>" + nl +
      "      <version>0.0.1</version>" + nl +
      "      <type>jar</type>" + nl +
      "    </dependency>" + nl
    );
  }

  @Test public void id2ver_opt() {
    poml.conf.append("depend=group.com:artifact:0.0.1::false");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "    <dependency>" + nl +
      "      <groupId>group.com</groupId>" + nl + 
      "      <artifactId>artifact</artifactId>" + nl +
      "      <version>0.0.1</version>" + nl +
      "      <optional>false</optional>" + nl +
      "    </dependency>" + nl
    );
  }
  
  @Test public void id2type() {
    poml.conf.append("depend=group.com:artifact:0.0.1:test:true:jar");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
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
    poml.conf.append("depend=");
    poml.conf.append("  demo.com:demo2,");
    poml.conf.append("  demo.com:demo:[4.12\\\\,),");
    poml.conf.append("  sample.com:sample:0.0.1:provided,");
    poml.conf.append("  group.com:artifact:0.0.1:test:true:jar");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
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
    poml.conf.load();
    try { 
      conv.convert(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Config not found");
    }
  }
  
  @Test public void ng_emptyConf() {
    poml.conf.append("depend=");
    poml.conf.load();
    try { 
      conv.convert(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config");
    }
  }
  
  @Test public void ng_badConf() {
    poml.conf.append("depend=");
    poml.conf.append("  group.com:artifact:1.0,");
    poml.conf.append("  group.com:");
    poml.conf.load();
    try { 
      conv.convert(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config");
    }
  }
  
  @Test public void ng_badConf2() {
    poml.conf.append("depend=, ,");
    poml.conf.append("  group.com:artifact:1.0");
    poml.conf.load();
    try { 
      conv.convert(poml, xml);
      fail();
    } catch (IllegalStateException e) {
      msg(e).starts("Bad config");
    }
  }
}
