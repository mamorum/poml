package poml.conv.basic;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import poml.conv.ConvTestCase;
import poml.conv.basic.Depend;

public class DependTest extends ConvTestCase {

  Depend conv = new Depend();
  
  @Test public void ng_noConf() {
    poml.conf.load();
    try { conv.convert(poml, xml); }
    catch (IllegalStateException e) {
      System.out.println(e.getMessage());
      assertThat(e.getMessage()).startsWith(
        "Config not found"
      );
    }
  }
  
  @Test public void ng_emptyConf() {
    poml.conf.append("depend=");
    poml.conf.load();
    try { conv.convert(poml, xml); }
    catch (IllegalStateException e) {
      System.out.println(e.getMessage());
      assertThat(e.getMessage()).startsWith(
        "Bad config val"
      );
    }
  }
  
  @Test public void ng_badConf() {
    poml.conf.append("depend=group.com:artifact:");
    poml.conf.load();
    try { conv.convert(poml, xml); }
    catch (IllegalStateException e) {
      System.out.println(e.getMessage());
      assertThat(e.getMessage()).startsWith(
        "Bad config val"
      );
    }
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
    poml.conf.append("  demo.com:demo:0.0.1,");
    poml.conf.append("  sample.com:sample:0.0.1:provided,");
    poml.conf.append("  group.com:artifact:0.0.1:test:true:jar");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "    <dependency>" + nl +
      "      <groupId>demo.com</groupId>" + nl + 
      "      <artifactId>demo</artifactId>" + nl +
      "      <version>0.0.1</version>" + nl +
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
}
