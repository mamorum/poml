package poml.conv.build.plugin;

import org.junit.Test;

import poml.conv.ConvTestCase;
import poml.conv.build.plugin.Fatjar;

public class FatjarTest extends ConvTestCase {

  Fatjar conv = new Fatjar();

  @Test public void defaultVer() {
    poml.conf.append("fatjar=");
    poml.conf.append("  mainClass:org.sample.Main");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
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
    poml.conf.append("fatjar=");
    poml.conf.append("  ver:1.0.0,");
    poml.conf.append("  jarName:sample.jar,");
    poml.conf.append("  mainClass:org.sample.Main");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
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
    poml.conf.append("fatjar=mainClass:org.sample.Main");
    poml.conf.append("fatjar.conf+={");
    poml.conf.append("  <outputDirectory>dist</outputDirectory>");
    poml.conf.append("  <delimiters>");
    poml.conf.append("    <delimiter>${*}</delimiter>");
    poml.conf.append("    <delimiter>@</delimiter>");
    poml.conf.append("  </delimiters>");
    poml.conf.append("}");
    poml.conf.append("fatjar.conf.archive+={");
    poml.conf.append("  <manifestEntries>");
    poml.conf.append("    <Built-By>Poml Authors</Built-By>");
    poml.conf.append("  </manifestEntries>");
    poml.conf.append("  <index>true</index>");
    poml.conf.append("}");
    poml.conf.load();
    conv.convert(poml, xml);
    output.is(
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
