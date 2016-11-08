package poml.in;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import poml.conv.Converters;
import poml.out.Xml;

// pom.poml
public class Poml {

  public Config conf = new Config();
  public Layout layout;
  
  public BufferedReader in;
  public String line;

  public static Poml open(String path) throws IOException {
    Poml p = new Poml();
    p.in = new BufferedReader(new InputStreamReader
      (new FileInputStream(path), "UTF-8")
    );
    return p;
  }

  public void loadConfig() throws IOException {
    while ((line = in.readLine()) != null) {
      if (line.trim().equals("---")) {
        this.layout = new Layout();
        break;  // -> has layout.
      }
      conf.append(line);
    }
    conf.load();
  }

  public void to(Xml xml) throws IOException {
    if (layout == null) {
      Converters.convert(this, xml);
      return;  // no layout
    }
    while ((line = in.readLine()) != null) {
      layout.processLine(this, xml);
    }
  }

  public void close() {
    try { if (in != null) in.close(); }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
