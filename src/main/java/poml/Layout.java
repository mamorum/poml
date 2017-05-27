package poml;

import java.io.IOException;

import poml.convert.Basic;
import poml.convert.Build;
import poml.convert.Env;
import poml.convert.More;
import poml.convert.Prj;

public class Layout {
  public static void none(Poml i, Xml o) {
    Prj.start(o);
    Basic.all(i, o);
    Build.all(i, o);
    More.all(i, o);
    Env.all(i, o);
    Prj.end(o);
  }

  public static void render(Poml poml, Xml xml) throws IOException {
    String line; int len; boolean nl;
    while ((line = poml.in.readLine()) != null) {
      len = line.length();
      if (len == 0) xml.nl();
      else {
        nl = line.endsWith("&");
        if (nl) line = line.substring(0, len-1);
        out(line, poml, xml);
        if (nl) xml.nl();
      }
    }
  }
  private static void out(String line, Poml i, Xml o) {
    int str = line.indexOf("{{");
    int end = line.indexOf("}}");
    if (str == -1 || end == -1) {
      o.line(line); return;  // no placeholder
    }
    // convert placeholder {{k}}
    String k = line.substring(str+2, end);
    if (Prj.start.equals(k)) Prj.start(o);
    else if (Prj.end.equals(k)) Prj.end(o);
    else if (Basic.pkg.equals(k)) Basic.pkg(i, o);
    else if (Basic.parent.equals(k)) Basic.parent(i, o);
    else if (Basic.dep.equals(k)) Basic.depend(i, o);
    else if (Basic.props.equals(k)) Basic.properties(i, o);
    else if (Build.plugin.equals(k)) Build.plugin(i, o);
    else if (More.info.equals(k)) More.info(i, o);
    else if (More.license.equals(k)) More.license(i, o);
    else if (More.dev.equals(k)) More.developer(i, o);
    else if (Env.issue.equals(k)) Env.issue(i, o);
    else if (Env.scm.equals(k)) Env.scm(i, o);
    else if (Env.dist.equals(k)) Env.dist(i, o);
    else Throw.noConv(k);
  }
}