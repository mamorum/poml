package poml.conv.build.plugin;

import org.junit.Ignore;
import org.junit.Test;

import poml.conv.ConvTestCase;
import poml.conv.build.plugin.Source;

@Ignore
public class SourceTest extends ConvTestCase {

  Source conv = new Source();

  @Ignore
  @Test public void defaults() {
//    poml.conf.append("&source=_default");
//    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "      <plugin>" + nl +
      "        <groupId>org.apache.maven.plugins</groupId>" + nl +
      "        <artifactId>maven-source-plugin</artifactId>" + nl +
      "        <version>3.0.1</version>" + nl +
      "        <executions>" + nl +
      "          <execution>" + nl +
      "            <id>attach-sources</id>" + nl +
      "            <goals><goal>jar-no-fork</goal></goals>" + nl +
      "          </execution>" + nl +
      "        </executions>" + nl +
      "      </plugin>" + nl
    );
  }

  @Ignore
  @Test public void ver() {
//    poml.conf.append("&source=ver:1.0.0");
//    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
      "      <plugin>" + nl +
      "        <groupId>org.apache.maven.plugins</groupId>" + nl +
      "        <artifactId>maven-source-plugin</artifactId>" + nl +
      "        <version>1.0.0</version>" + nl +
      "        <executions>" + nl +
      "          <execution>" + nl +
      "            <id>attach-sources</id>" + nl +
      "            <goals><goal>jar-no-fork</goal></goals>" + nl +
      "          </execution>" + nl +
      "        </executions>" + nl +
      "      </plugin>" + nl
    );
  }

  @Ignore
  @Test public void ng_noConf() {
//    poml.conf.load();
    try {conv.convert(poml, xml);}
    catch (IllegalStateException e) {
      msg(e).starts("Config not found");
    }
  }
}
