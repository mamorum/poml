package poml.io;

import java.io.BufferedReader;
import java.io.IOException;

import poml.convert.Basic;
import poml.convert.Build;
import poml.convert.Env;
import poml.convert.More;
import poml.convert.Prj;

// pom.poml
public class Poml {
  public Conf conf = new Conf();
  private boolean hasLayout = false;
  private BufferedReader in;
  private String line;

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

  //-> Config Section
  private void parseConfig() throws IOException {
    while ((line = in.readLine()) != null) {
      if (line.length() == 0) continue;  // none
      if (line.charAt(0) == '#') continue;  // comment
      if (line.equals("---")) {  // layout
        this.hasLayout = true;
        break;
      }
      char last = line.charAt(line.length()-1);
      if (last == '{') addXml();
      else if (isContinuing(last)) addLines();
      else addLine(line);
    }
  }
  private void addXml() throws IOException {
    // first line
    int pos = line.indexOf('=');
    if (pos == -1) return;  // TODO no key -> throw?
    String key = line.substring(0, pos).trim();
    // second+ lines
    StringBuilder tags = new StringBuilder();
    while ((line = in.readLine()) != null) {
      if ("}".equals(line)) break;
      tags.append(line).append(
        System.lineSeparator()
      );
    }
    conf.kv.put(key, tags.toString());
  }
  private boolean isContinuing(char last) {
    if (last == '=') return true;
    if (last == ',') return true;
    return false;
  }
  private void addLines() throws IOException {
    StringBuilder sb = new StringBuilder(line);
    char last;
    while ((line = in.readLine()) != null) {
      sb.append(line);
      last = line.charAt(line.length()-1);
      if (isContinuing(last)) continue;
      break;
    }
    addLine(sb.toString());
  }
  private void addLine(String l) {
    int pos = l.indexOf('=');
    if (pos == -1) return;  // TODO no key -> throw?
    String k = l.substring(0, pos).trim();
    String v = l.substring(pos + 1);
    conf.kv.put(k, v);
  }

  //-> Layout Section
  private void noLayoutTo(Xml xml) {
    Prj.start(xml);
    Basic.all(this, xml);
    Build.all(this, xml);
    More.all(this, xml);
    Env.all(this, xml);
    Prj.end(xml);
  }

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
    else if (Basic.dep.equals(key)) Basic.depend(this, o);
    else if (Basic.prop.equals(key)) Basic.properties(this, o);
    else if (Build.plugin.equals(key)) Build.plugin(this, o);
    else if (More.info.equals(key)) More.info(this, o);
    else if (More.lic.equals(key)) More.license(this, o);
    else if (More.dev.equals(key)) More.developer(this, o);
    else if (Env.issue.equals(key)) Env.issue(this, o);
    else if (Env.scm.equals(key)) Env.scm(this, o);
    else if (Env.dist.equals(key)) Env.dist(this, o);
    else throw new IllegalStateException(
      "{{" + key  + "}} cannot be used in poml"
    );
  }
}