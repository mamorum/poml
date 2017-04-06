package poml.conv.build.plugin;

import org.junit.Test;

import poml.conv.ConvTestCase;
import poml.conv.build.plugin.Gpg;

public class GpgTest extends ConvTestCase {

  Gpg conv = new Gpg();

  @Test public void defaults() {
    poml.conf.append("&gpg=_default");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "      <plugin>" + nl +
      "        <groupId>org.apache.maven.plugins</groupId>" + nl +
      "        <artifactId>maven-gpg-plugin</artifactId>" + nl +
      "        <version>1.6</version>" + nl +
      "        <executions>" + nl +
      "          <execution>" + nl +
      "            <id>sign-artifacts</id>" + nl +
      "            <phase>verify</phase>" + nl +
      "            <goals><goal>sign</goal></goals>" + nl +
      "          </execution>" + nl +
      "        </executions>" + nl +
      "      </plugin>" + nl
    );
  }

  @Test public void ver() {
    poml.conf.append("&gpg=ver:1.0.0");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "      <plugin>" + nl +
      "        <groupId>org.apache.maven.plugins</groupId>" + nl +
      "        <artifactId>maven-gpg-plugin</artifactId>" + nl +
      "        <version>1.0.0</version>" + nl +
      "        <executions>" + nl +
      "          <execution>" + nl +
      "            <id>sign-artifacts</id>" + nl +
      "            <phase>verify</phase>" + nl +
      "            <goals><goal>sign</goal></goals>" + nl +
      "          </execution>" + nl +
      "        </executions>" + nl +
      "      </plugin>" + nl
    );
  }

  @Test public void ng_noConf() {
    poml.conf.load();
    try {conv.convert(poml, xml);}
    catch (IllegalStateException e) {
      msg(e).starts("Config not found");
    }
  }

  @Test public void ng_emptyConf() {
    poml.conf.append("&gpg=");
    poml.conf.load();
    try {conv.convert(poml, xml);}
    catch (IllegalStateException e) {
      msg(e).starts("Bad config");
    }
  }

  @Test public void ng_badConf() {
    poml.conf.append("&gpg=ver:1.0, ngValue");
    poml.conf.load();
    try {conv.convert(poml, xml);}
    catch (IllegalStateException e) {
      msg(e).starts("Bad config");
    }
  }
}
