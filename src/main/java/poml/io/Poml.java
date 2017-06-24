package poml.io;

import java.io.BufferedReader;
import java.io.IOException;

import poml.convert.Basic;
import poml.convert.Build;
import poml.convert.Env;
import poml.convert.More;
import poml.convert.Prj;

// pom.poml & conversion tool (parse, render)
public class Poml {
  public Conf conf = new Conf();
  boolean hasLayout = false;
  private BufferedReader in;
  private String line;
  private int ieq;

  private Poml(BufferedReader in) {this.in = in;}
  public static Poml of(BufferedReader in) throws IOException {
    Poml p = new Poml(in);
    p.parseConfig();
    return p;
  }
  public String toXml() throws IOException {
    Xml xml = new Xml();
    if (hasLayout) layoutTo(xml);
    else noLayoutTo(xml);
    return xml.toString();
  }

  //-> Parse: Config Section
  private void parseConfig() throws IOException {
    while ((line = in.readLine()) != null) {
      if (line.length() == 0) continue;  // none
      if (line.charAt(0) == '#') continue;  // comment
      if (line.equals("---")) {  // layout
        this.hasLayout = true; break;
      }
      ieq = line.indexOf('=');
      if (ieq <= 0) noKey(line);
      String key = line.substring(0, ieq);
      String val = line.substring(ieq+1);
      if (eqEnd()) val = lines(""); // key=
      else if (csvEnd()) val = lines(val); // key=.... ,
      else if (xmlEnd()) val = xml(); // key={
      conf.kv.put(key, val); // key=val
    }
  }
  private boolean eqEnd() {
    int ilast = line.length() - 1;
    return (ilast == ieq);
  }
  private boolean csvEnd() {
    char last1 = line.charAt(line.length() - 1);
    char last2 = line.charAt(line.length() - 2);
    if (last2 != '\\' && last1 == ',') return true;
    return false;
  }
  private String lines(String l) throws IOException {
    StringBuilder lines = new StringBuilder(l);
    while ((line = in.readLine()) != null) {
      lines.append(line);
      if (csvEnd()) continue;
      else break;
    }
    return lines.toString();
  }
  private boolean xmlEnd() {
    int ilast2 = line.length() - 2;
    char last1 = line.charAt(line.length() - 1);
    if (ilast2 == ieq && last1 == '{') return true;
    return false;
  }
  private String xml() throws IOException {
    StringBuilder xml = new StringBuilder();
    while ((line = in.readLine()) != null) {
      if ("}".equals(line)) break;
      else xml.append(line).append(
        System.lineSeparator()
      );
    }
    return xml.toString();
  }
  private void noKey(String l) {
    throw new RuntimeException(
      "Invalid config [line=" + l + "]"
    );
  }

  //-> Render: Layout Section
  private void layoutTo(Xml xml) throws IOException {
    boolean nl;
    while ((line = in.readLine()) != null) {
      nl = line.endsWith("&");
      if (nl) line = line.substring(
        0, line.length() - 1
      );
      render(line, xml);
      if (nl) xml.nl();
    }
  }
  private void render(String l, Xml o) {
    int str = l.indexOf("{{");
    int end = l.indexOf("}}");
    if (str == -1 || end == -1) {
      o.line(l); return;  // no placeholder
    }
    // convert placeholder {{key}}
    String key = l.substring(str+2, end);
    if (Prj.start.equals(key)) Prj.start(o);
    else if (Prj.end.equals(key)) Prj.end(o);
    else if (Basic.pkg.equals(key)) Basic.pkg(this, o);
    else if (Basic.parent.equals(key)) Basic.parent(this, o);
    else if (Basic.depend.equals(key)) Basic.depend(this, o);
    else if (Basic.properties.equals(key)) Basic.properties(this, o);
    else if (Build.plugin.equals(key)) Build.plugin(this, o);
    else if (More.info.equals(key)) More.info(this, o);
    else if (More.license.equals(key)) More.license(this, o);
    else if (More.developer.equals(key)) More.developer(this, o);
    else if (Env.issue.equals(key)) Env.issue(this, o);
    else if (Env.scm.equals(key)) Env.scm(this, o);
    else if (Env.dist.equals(key)) Env.dist(this, o);
    else throw new IllegalStateException(
      "{{" + key  + "}} cannot be used in poml"
    );
  }
  private void noLayoutTo(Xml xml) {
    Prj.start(xml);
    Basic.all(this, xml);
    Build.all(this, xml);
    More.all(this, xml);
    Env.all(this, xml);
    Prj.end(xml);
  }
}