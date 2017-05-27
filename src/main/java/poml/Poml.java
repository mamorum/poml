package poml;

import java.io.BufferedReader;
import java.io.IOException;

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
    if (conf.hasLayout) Layout.render(this, xml);
    else Layout.none(this, xml);
    return xml.toString();
  }
}
