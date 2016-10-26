package poml.in;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import poml.out.Xml;
import poml.tool.converter.Tmpl.Reader;

// pom.poml
public class Poml {

  public BufferedReader in;
  public String line;

  public Config conf = new Config();
  public Layout layout = new Layout();
  
  public static Poml open(String path) throws IOException {
    Poml p = new Poml();
    p.in = new BufferedReader(new InputStreamReader
      (new FileInputStream(path), "UTF-8")
    );
    return p;
  }
  
  public void loadConfig() throws IOException {
    while ((line = in.readLine()) != null) {
      if (line.equals("---")) break;
      conf.append(line);
    }
    conf.load();
  }

  public void layoutTo(Xml xml) throws IOException {
    if (line == null) defaultLayout();
    while ((line = in.readLine()) != null) {
      layout.processLine(this, xml);
    }
  }
  
  private void defaultLayout() {
    close();
    in = Reader.from("/in/layout/default.txt");
  }

  public void close() {
    try { if (in != null) in.close(); }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
