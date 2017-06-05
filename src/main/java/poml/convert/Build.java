package poml.convert;

import poml.Throw;
import poml.io.Poml;
import poml.io.Xml;

public class Build {
  public static final String plugin="plugin";

  public static void all(Poml in, Xml out) {
    if (in.conf.has("plugin")) {
      out.nl();
      out.line("  <build>");
      out.line("    <plugins>");
      Build.plugin(in, out);
      out.line("    </plugins>");
      out.line("  </build>");
    }
  }

  // -> plugin
  private static final String fatjar="&fatjar", ossrh="&ossrh";
  public static void plugin(Poml in, Xml out) {
    String[] plgs = in.conf.vals(plugin);
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
  private static void $plugin(String $plg, Poml in, Xml out) {
    String val = in.conf.val($plg);
    String[] vals = val.split(":");
    out.line("      <plugin>");
    out.tags(Xml.sp8, keys, vals);  // groupId - inherited
    $conf($plg, in, out);
    $depends($plg, in, out);
    $execs($plg, in, out);
    out.line("      </plugin>");
  }
  private static void $conf(String $plg, Poml in, Xml out) {
    String key = (new StringBuilder($plg)).append(".conf").toString();
    if (in.conf.has(key)) {
      out.line("        <configuration>");
      out.xml(Xml.sp8, in.conf.xml(key));
      out.line("        </configuration>");
    }
  }
  private static void $depends(String $plg, Poml in, Xml out) {
    String key = (new StringBuilder($plg)).append(".depends").toString();
    if (in.conf.has(key)) {
      out.line("        <dependencies>");
      out.xml(Xml.sp8, in.conf.xml(key));
      out.line("        </dependencies>");
    }
  }
  private static void $execs(String $plg, Poml in, Xml out) {
    String key = (new StringBuilder($plg)).append(".execs").toString();
    if (in.conf.has(key)) {
      out.line("        <executions>");
      out.xml(Xml.sp8, in.conf.xml(key));
      out.line("        </executions>");
    }
  }

  // -> &fatjar : render assembly-plugin
  private static void fatjar(Poml in, Xml out) {
    String ver=null, jar=null, main=null;
    for (String k: in.conf.vals(fatjar)) {
      if (k.startsWith("version")) ver=k;
      else if (k.startsWith("finalName")) jar=k;
      else if (k.startsWith("mainClass")) main=k;
    }
    if (ver == null) ver = "version>2.6";
    if (jar == null) jar = "finalName>${project.artifactId}";
    if (main == null) Throw.val(fatjar, in.conf.val(fatjar)); // required
    // -> render
    out.line("      <plugin>");
    out.line("        <groupId>org.apache.maven.plugins</groupId>");
    out.line("        <artifactId>maven-assembly-plugin</artifactId>");
    out.txt("        <").txt(ver).txt("</version>").nl();
    out.line("        <configuration>");
    out.txt("          <").txt(jar).txt("</finalName>").nl();
    out.line("          <descriptorRefs>");
    out.line("            <descriptorRef>jar-with-dependencies</descriptorRef>");
    out.line("          </descriptorRefs>");
    out.line("          <appendAssemblyId>false</appendAssemblyId>");
    out.line("          <attach>false</attach>");
    out.line("          <archive>");
    out.line("            <manifest>");
    out.txt("              <").txt(main).txt("</mainClass>").nl();
    out.line("            </manifest>");
    String confArc = "&fatjar.conf.archive+";
    if (in.conf.has(confArc)) {
      out.xml(Xml.sp10, in.conf.xml(confArc));
    }
    out.line("          </archive>");
    String conf = "&fatjar.conf+";
    if (in.conf.has(conf)) {
      out.xml(Xml.sp8, in.conf.xml(conf));
    }
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
  private static void ossrh(Xml out) {
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
