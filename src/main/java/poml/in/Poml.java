package poml.in;

import java.io.BufferedReader;
import java.io.IOException;

import poml.conv.Convert;
import poml.out.Xml;

// pom.poml & its conversion tool
public class Poml {
  private BufferedReader in;
  private Poml(BufferedReader in) { this.in = in; }
  public Config conf = new Config();

  public static Poml parse(BufferedReader in) {
    Poml p = new Poml(in);
    p.conf.parse(in);
    return p;
  }

  public String toXml() throws IOException {
    Xml xml = new Xml();
    if (conf.hasLayout) layoutTo(xml);
    else noLayoutTo(xml);
    return xml.toString();
  }
  private void layoutTo(Xml xml)  throws IOException {
    String line;
    while ((line = in.readLine()) != null) {
      if (line.length() == 0) {
        xml.nl(); continue;
      }
      boolean endsAmp = line.endsWith("&");
      int start = line.indexOf("{{");
      int end = line.indexOf("}}");
      if (start == -1 || end == -1) { // not convert
        if (endsAmp) line = line.substring(0, line.length()-1);
        xml.line(line);
      } else {  // convert
        String name = line.substring(start+2, end);
        Convert.exec(name, this, xml);
      }
      if (endsAmp) xml.nl();
    }
  }
  private void noLayoutTo(Xml xml) {
    Convert.start(this, xml);
    Convert.basic(this, xml);
    Convert.build(this, xml);
    Convert.more(this, xml);
    Convert.env(this, xml);
    Convert.end(this, xml);
  }
}
