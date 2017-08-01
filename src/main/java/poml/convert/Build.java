package poml.convert;

import poml.io.Poml;
import poml.io.Xml;

public class Build {
  public static final String builds="builds";
  public static final String plugin="plugin";

  public static void all(Poml in, Xml out) {
    boolean b = in.conf.has(builds);
    boolean p = in.conf.has(plugin);
    boolean exist = b || p;
    if (exist) {
      out.nl();
      out.line("  <build>");
      if (b) builds(in, out);
      if (p) {
        out.line("    <plugins>");
        plugin(in, out);
        out.line("    </plugins>");
      }
      out.line("  </build>");
    }
  }

  // -> builds=k>v, k>v ...
  public static void builds(Poml in, Xml out) {
    String[] kv = in.conf.csv(builds);
    out.kv(Xml.sp4, kv);
  }

  // -> plugin=plg, plg ...
  private static final String fatjar="&fatjar", ossrh="&ossrh";
  public static void plugin(Poml in, Xml out) {
    for (String plg: in.conf.csv(plugin)) {
      if (plg.equals(fatjar)) fatjar(in, out);
      else if (plg.equals(ossrh)) ossrh(out);
      else plg(plg, in, out);
    }
  }
  /// plg=v:v:v...
  /// plg.conf={
  ///   <key>val</key>
  /// }
  /// plg.depends={
  ///   <key>val</key>
  /// }
  /// plg.execs={
  ///   <key>val</key>
  /// }
  private static final String[] plgTags = {
    "groupId", "artifactId", "version", "extensions", "inherited"};
  private static void plg(String plg, Poml in, Xml out) {
    out.line("      <plugin>");
    String val = in.conf.val(plg);
    String[] v = val.split(":");
    out.tags(Xml.sp8, plgTags, v);
    String conf = key(plg, ".conf");
    String depends = key(plg, ".depends");
    String execs = key(plg, ".execs");
    xml(conf, "configuration", in, out);
    xml(depends, "dependencies", in, out);
    xml(execs, "executions", in, out);
    out.line("      </plugin>");
  }
  private static String key(String plg, String suffix) {
    return (new StringBuilder(plg)).append(suffix).toString();
  }
  private static void xml(String key, String tag, Poml in, Xml out) {
    if (in.conf.has(key)) {
      out.txt("        <").txt(tag).line(">");
      out.xml(Xml.sp8, in.conf.xml(key));
      out.txt("        </").txt(tag).line(">");
    }
  }

  /// plugin=&fatjar
  /// &fatjar=k>v, k>v ...
  /// &fatjar.conf+={
  ///   <key>val</key>
  /// }
  /// &fatjar.conf.archive+={
  ///   <key>val</key>
  /// }
  private static void fatjar(Poml in, Xml out) {
    String ver=null, name=null, main=null;
    if (in.conf.has(fatjar)) {
      for (String kv: in.conf.csv(fatjar)) {
        if (kv.startsWith("version>")) ver=kv;
        else if (kv.startsWith("finalName>")) name=kv;
        else if (kv.startsWith("mainClass>")) main=kv;
      }
    }
    if (ver == null) ver = "version>2.6";
    if (name == null) name = "finalName>${project.artifactId}";
    out.line("      <plugin>");
    out.line("        <groupId>org.apache.maven.plugins</groupId>");
    out.line("        <artifactId>maven-assembly-plugin</artifactId>");
    out.txt("        <").txt(ver).txt("</version>").nl();
    out.line("        <configuration>");
    out.txt("          <").txt(name).txt("</finalName>").nl();
    fatjarConf(in, out);
    fatjarConfArchive(in, out, main);
    out.line("          <descriptorRefs>");
    out.line("            <descriptorRef>jar-with-dependencies</descriptorRef>");
    out.line("          </descriptorRefs>");
    out.line("          <appendAssemblyId>false</appendAssemblyId>");
    out.line("          <attach>false</attach>");
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
  private static final String fatjarConf = "&fatjar.conf+";
  private static void fatjarConf(Poml in, Xml out) {
    if (in.conf.has(fatjarConf)) {
      out.xml(Xml.sp8, in.conf.xml(fatjarConf));
    }
  }
  private static final String fatjarConfArchive = "&fatjar.conf.archive+";
  private static void fatjarConfArchive(Poml in, Xml out, String main) {
    boolean hasMain = (main != null);
    boolean hasAdding = in.conf.has(fatjarConfArchive);
    if (!hasMain && !hasAdding) return;
    out.line("          <archive>");
    if (hasMain) {
      out.line("            <manifest>");
      out.txt("              <").txt(main).txt("</mainClass>").nl();
      out.line("            </manifest>");
    }
    if (hasAdding) {
      out.xml(Xml.sp10, in.conf.xml(fatjarConfArchive));
    }
    out.line("          </archive>");
  }

  /// plugin=&ossrh
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