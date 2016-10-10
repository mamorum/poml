package poml.converter.build.plugin;

import org.junit.Test;

import poml.ConverterCase;

public class FatjarTest extends ConverterCase {

  Fatjar conveter = new Fatjar();

  @Test public void defaultVer() {
    poml.conf.p.put("fatjar",
      "  jarName:${project.artifactId}," + 
      "  mainClass:org.sample.Main");
    conveter.convert(poml, xml);
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
  
  @Test public void ver() {
    poml.conf.p.put("fatjar",
        "  ver:1.0.0," +
        "  jarName:${project.artifactId}," + 
        "  mainClass:org.sample.Main");
    conveter.convert(poml, xml);
    output.is(
        "      <plugin>" + nl + 
        "        <groupId>org.apache.maven.plugins</groupId>" + nl +
        "        <artifactId>maven-assembly-plugin</artifactId>" + nl +
        "        <version>1.0.0</version>" + nl +
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
}
