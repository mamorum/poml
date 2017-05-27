package poml;

import java.io.IOException;

import poml.convert.Basic;
import poml.convert.Build;
import poml.convert.Env;
import poml.convert.More;
import poml.convert.Prj;

public class Layout {
  public static void convert(Poml poml, Xml xml) throws IOException {
    String line;
    while ((line = poml.in.readLine()) != null) {
      if (line.length() == 0) {
        xml.nl(); continue;
      }
      boolean endsAmp = line.endsWith("&");
      int start = line.indexOf("{{");
      int end = line.indexOf("}}");
      if (start == -1 || end == -1) { // no placeholder
        if (endsAmp) {
          line = line.substring(0, line.length()-1);
        }
        xml.line(line);
      } else {  // convert placeholder
        String key = line.substring(start+2, end);
        convert(key, poml, xml);
      }
      if (endsAmp) xml.nl();
    }
  }
  private static void convert(String k, Poml i, Xml o) {
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