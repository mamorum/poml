package poml.conv.build;

import java.util.Map;

import poml.conv.Converter;
import poml.io.Poml;
import poml.io.Xml;
import poml.util.Throw;

public class Plugin  implements Converter {
  private static final String fatjar="&fatjar", ossrh="&ossrh";

  @Override public String name() { return "plugin"; }

  @Override public void convert(Poml in, Xml out) {
    String[] plgs = in.conf.vals(name());
    for (String plg: plgs) {
      if (fatjar.equals(plg)) fatjar(in, out);
      else if (ossrh.equals(plg)) ossrh(out);
      else $plugin(plg, in, out);
    }
  }

  // -> $plugin :  ex. $compiler
  private static final String[] keys = {
    "groupId", "artifactId", "version", "extensions", "inherited"
  };
  private void $plugin(String $plg, Poml in, Xml out) {
    String val = in.conf.val($plg);
    String[] vals = val.split(":");
    out.line("      <plugin>");
    out.tags(sp8, keys, vals);  // groupId - inherited
    $conf($plg, in, out);
    $depends($plg, in, out);
    $execs($plg, in, out);
    out.line("      </plugin>");
  }
  private void $conf(String $plg, Poml in, Xml out) {
    String key = (new StringBuilder($plg)).append(".conf").toString();
    if (in.conf.hasTag(key)) {
      out.line("        <configuration>");
      out.txt(in.conf.tag(key, sp8));
      out.line("        </configuration>");
    }
  }
  private void $depends(String $plg, Poml in, Xml out) {
    String key = (new StringBuilder($plg)).append(".depends").toString();
    if (in.conf.hasTag(key)) {
      out.line("        <dependencies>");
      out.txt(in.conf.tag(key, sp8));
      out.line("        </dependencies>");
    }
  }
  private void $execs(String $plg, Poml in, Xml out) {
    String key = (new StringBuilder($plg)).append(".execs").toString();
    if (in.conf.hasTag(key)) {
      out.line("        <executions>");
      out.txt(in.conf.tag(key, sp8));
      out.line("        </executions>");
    }
  }

  // -> &fatjar : render assembly-plugin
  private void fatjar(Poml in, Xml out) {
    Map<String, String> map = in.conf.map(fatjar);
    String main = map.get("mainClass");  // required
    if (main == null) Throw.noKv(fatjar, "mainClass");
    String ver = map.get("ver");
    if (ver == null) ver = "2.6";
    String jar = map.get("jarName");
    if (jar == null) jar = "${project.artifactId}";
    // -> render
    out.line("      <plugin>");
    out.line("        <groupId>org.apache.maven.plugins</groupId>");
    out.line("        <artifactId>maven-assembly-plugin</artifactId>");
    out.txt("        <version>").txt(ver).txt("</version>").nl();
    out.line("        <configuration>");
    out.txt("          <finalName>").txt(jar).txt("</finalName>").nl();
    out.line("          <descriptorRefs>");
    out.line("            <descriptorRef>jar-with-dependencies</descriptorRef>");
    out.line("          </descriptorRefs>");
    out.line("          <appendAssemblyId>false</appendAssemblyId>");
    out.line("          <attach>false</attach>");
    out.line("          <archive>");
    out.line("            <manifest>");
    out.txt("              <mainClass>").txt(main).txt("</mainClass>").nl();
    out.line("            </manifest>");
    String confArc = "&fatjar.conf.archive+";
    if (in.conf.hasTag(confArc)) out.txt(in.conf.tag(confArc, sp10));
    out.line("          </archive>");
    String conf = "&fatjar.conf+";
    if (in.conf.hasTag(conf)) out.txt(in.conf.tag(conf, sp8));
    out.line("        </configuration>");
    out.line("        <executions>");
    out.line("          <execution>");
    out.line("            <id>make-assembly</id>");
    out.line("            <phase>package</phase>");
    out.line("            <goals><goal>single</goal></goals>");
    out.line("          </execution>");
    out.line("        </executions>");
    out.line("      </plugin>");
  }

  // -> &ossrh : render javadoc, source, and gpg
  // http://central.sonatype.org/pages/apache-maven.html
  private void ossrh(Xml out) {
    out.line("      <plugin>");
    out.line("        <groupId>org.apache.maven.plugins</groupId>");
    out.line("        <artifactId>maven-source-plugin</artifactId>");
    out.line("        <version>2.2.1</version>");
    out.line("        <executions>");
    out.line("          <execution>");
    out.line("            <id>attach-sources</id>");
    out.line("            <goals><goal>jar-no-fork</goal></goals>");
    out.line("          </execution>");
    out.line("        </executions>");
    out.line("      </plugin>");
    out.line("      <plugin>");
    out.line("        <groupId>org.apache.maven.plugins</groupId>");
    out.line("        <artifactId>maven-javadoc-plugin</artifactId>");
    out.line("        <version>2.9.1</version>");
    out.line("        <executions>");
    out.line("          <execution>");
    out.line("            <id>attach-javadocs</id>");
    out.line("            <goals><goal>jar</goal></goals>");
    out.line("          </execution>");
    out.line("        </executions>");
    out.line("      </plugin>");
    out.line("      <plugin>");
    out.line("        <groupId>org.apache.maven.plugins</groupId>");
    out.line("        <artifactId>maven-gpg-plugin</artifactId>");
    out.line("        <version>1.5</version>");
    out.line("        <executions>");
    out.line("          <execution>");
    out.line("            <id>sign-artifacts</id>");
    out.line("            <phase>verify</phase>");
    out.line("            <goals><goal>sign</goal></goals>");
    out.line("          </execution>");
    out.line("        </executions>");
    out.line("      </plugin>");
  }
}
