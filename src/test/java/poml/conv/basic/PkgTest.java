package poml.conv.basic;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import poml.conv.ConvTestCase;
import poml.conv.basic.Pkg;

public class PkgTest extends ConvTestCase {

  Pkg conv = new Pkg();
  
  @Test public void id_ver() {
    poml.conf.append("pkg=group.com:artifact:0.0.1");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "  <groupId>group.com</groupId>" + nl + 
      "  <artifactId>artifact</artifactId>" + nl +
      "  <version>0.0.1</version>" + nl
    );
  }
  
  @Test public void id_ver_pkg() {
    poml.conf.append("pkg=group.com:artifact:0.0.1:jar");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "  <groupId>group.com</groupId>" + nl + 
      "  <artifactId>artifact</artifactId>" + nl +
      "  <version>0.0.1</version>" + nl +
      "  <packaging>jar</packaging>" + nl
    );
  }
  
  @Test public void ng_noConf() {
    poml.conf.load();
    try { conv.convert(poml, xml); }
    catch (IllegalStateException e) {
      System.out.println(e.getMessage());
      assertThat(e.getMessage()).startsWith(
        "No config"
      );
    }
  }

  @Test public void ng_emptyConf() {
    poml.conf.append("pkg=");
    poml.conf.load();
    try { conv.convert(poml, xml); }
    catch (IllegalStateException e) {
      System.out.println(e.getMessage());
      assertThat(e.getMessage()).startsWith(
        "Bad config"
      );
    }
  }

  @Test public void ng_badConf() {
    poml.conf.append("pkg=group.com:::");
    poml.conf.load();
    try { conv.convert(poml, xml); }
    catch (IllegalStateException e) {
      System.out.println(e.getMessage());
      assertThat(e.getMessage()).startsWith(
        "Bad config"
      );
    }
  }
}
