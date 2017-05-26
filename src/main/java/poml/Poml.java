package poml;

import java.io.BufferedReader;
import java.io.IOException;

import poml.convert.Basic;
import poml.convert.Build;
import poml.convert.Env;
import poml.convert.More;
import poml.convert.Prj;

// pom.poml & its conversion tool
public class Poml {
  public BufferedReader in;
  public Config conf = new Config();

  private Poml(BufferedReader in) { this.in = in; }
  public static Poml parse(BufferedReader in) {
    Poml p = new Poml(in);
    p.conf.parse(in);
    return p;
  }

  public String toXml() throws IOException {
    Xml xml = new Xml();
    if (conf.hasLayout) {
      Layout.convert(this, xml);
    } else {  // no layout
      Prj.to(xml);
      Basic.all(this, xml);
      Build.all(this, xml);
      More.all(this, xml);
      Env.all(this, xml);
      xml.txt("</project>");
    }
    return xml.toString();
  }
}
