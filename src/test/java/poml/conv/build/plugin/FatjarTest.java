package poml.conv.build.plugin;

import org.junit.Test;

import poml.conv.ConvTestCase;
import poml.conv.build.plugin.Fatjar;

public class FatjarTest extends ConvTestCase {

  Fatjar conv = new Fatjar();

  @Test public void defaultVer() {
    poml.conf.parse(data(
      "&fatjar="+ nl +
      "  mainClass:org.sample.Main"+ nl
    ));
    conv.convert(poml, xml);
    result(
      "      <plugin>" + nl + 
      "        <groupId>org.apache.maven.plugins</groupId>" + nl +
      "        <artifactId>maven-assembly-plugin</artifactId>" + nl +
      "        <version>2.6</version>" + nl +
      "        <configuration>" + nl +
      "          <finalName>${project.artifactId}</finalName>" + nl +
      "          <descriptorRefs>" + nl +
      "            <descriptorRef>jar-with-dependencies</descriptorRef>" + nl +
      "          </descriptorRefs>" + nl +
      "          <appendAssemblyId>false</appendAssemblyId>" + nl +
      "          <attach>false</attach>" + nl +
      "          <archive>" + nl +
      "            <manifest>" + nl +
      "              <mainClass>org.sample.Main</mainClass>" + nl +
      "            </manifest>" + nl +
      "          </archive>" + nl +
      "        </configuration>" + nl +
      "        <executions>" + nl +
      "          <execution>" + nl +
      "            <id>make-assembly</id>" + nl +
      "            <phase>package</phase>" + nl +
      "            <goals><goal>single</goal></goals>" + nl +
      "          </execution>" + nl +
      "        </executions>" + nl +
      "      </plugin>" + nl
    );
  }
  
  @Test public void ver_jar() {
    poml.conf.parse(data(
      "&fatjar="+ nl +
      "  ver:1.0.0,"+ nl +
      "  jarName:sample.jar,"+ nl +
      "  mainClass:org.sample.Main"+ nl
    ));
    conv.convert(poml, xml);
    result(
      "      <plugin>" + nl + 
      "        <groupId>org.apache.maven.plugins</groupId>" + nl +
      "        <artifactId>maven-assembly-plugin</artifactId>" + nl +
      "        <version>1.0.0</version>" + nl +
      "        <configuration>" + nl +
      "          <finalName>sample.jar</finalName>" + nl +
      "          <descriptorRefs>" + nl +
      "            <descriptorRef>jar-with-dependencies</descriptorRef>" + nl +
      "          </descriptorRefs>" + nl +
      "          <appendAssemblyId>false</appendAssemblyId>" + nl +
      "          <attach>false</attach>" + nl +
      "          <archive>" + nl +
      "            <manifest>" + nl +
      "              <mainClass>org.sample.Main</mainClass>" + nl +
      "            </manifest>" + nl +
      "          </archive>" + nl +
      "        </configuration>" + nl +
      "        <executions>" + nl +
      "          <execution>" + nl +
      "            <id>make-assembly</id>" + nl +
      "            <phase>package</phase>" + nl +
      "            <goals><goal>single</goal></goals>" + nl +
      "          </execution>" + nl +
      "        </executions>" + nl +
      "      </plugin>" + nl
    );
  }
  
  @Test public void addConfArch() {
    poml.conf.parse(data(
      "&fatjar=mainClass:org.sample.Main"+ nl +
      "&fatjar.conf+={"+ nl +
      "  <outputDirectory>dist</outputDirectory>"+ nl +
      "  <delimiters>"+ nl +
      "    <delimiter>${*}</delimiter>"+ nl +
      "    <delimiter>@</delimiter>"+ nl +
      "  </delimiters>"+ nl +
      "}"+ nl +
      "&fatjar.conf.archive+={"+ nl +
      "  <manifestEntries>"+ nl +
      "    <Built-By>Poml Authors</Built-By>"+ nl +
      "  </manifestEntries>"+ nl +
      "  <index>true</index>"+ nl +
      "}"+ nl
    ));
    conv.convert(poml, xml);
    result(
      "      <plugin>" + nl + 
      "        <groupId>org.apache.maven.plugins</groupId>" + nl +
      "        <artifactId>maven-assembly-plugin</artifactId>" + nl +
      "        <version>2.6</version>" + nl +
      "        <configuration>" + nl +
      "          <finalName>${project.artifactId}</finalName>" + nl +
      "          <descriptorRefs>" + nl +
      "            <descriptorRef>jar-with-dependencies</descriptorRef>" + nl +
      "          </descriptorRefs>" + nl +
      "          <appendAssemblyId>false</appendAssemblyId>" + nl +
      "          <attach>false</attach>" + nl +
      "          <archive>" + nl +
      "            <manifest>" + nl +
      "              <mainClass>org.sample.Main</mainClass>" + nl +
      "            </manifest>" + nl +
      "            <manifestEntries>" + nl +
      "              <Built-By>Poml Authors</Built-By>" + nl +
      "            </manifestEntries>" + nl +
      "            <index>true</index>" + nl +
      "          </archive>" + nl +
      "          <outputDirectory>dist</outputDirectory>" + nl +
      "          <delimiters>" + nl +
      "            <delimiter>${*}</delimiter>" + nl +
      "            <delimiter>@</delimiter>" + nl +
      "          </delimiters>" + nl +
      "        </configuration>" + nl +
      "        <executions>" + nl +
      "          <execution>" + nl +
      "            <id>make-assembly</id>" + nl +
      "            <phase>package</phase>" + nl +
      "            <goals><goal>single</goal></goals>" + nl +
      "          </execution>" + nl +
      "        </executions>" + nl +
      "      </plugin>" + nl
    );
  }
}
