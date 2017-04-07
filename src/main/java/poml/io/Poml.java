package poml.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import poml.conv.Converters;

// pom.poml
public class Poml {
  
  public BufferedReader in;
  public String line;

  public Config conf = new Config();
  public Layout layout;

  public void open(String path) throws IOException {
    in = new BufferedReader(new InputStreamReader
      (new FileInputStream(path), "UTF-8")
    );
  }
  public void close() throws IOException{
    if (in != null) in.close();
  }

  public void loadConfig() throws IOException {
    while ((line = in.readLine()) != null) {
      if (line.equals("---")) {
        layout = new Layout();
        break;  // layout exists.
      }
      conf.append(line);
    }
    conf.load();
  }
  public void to(Xml xml) throws IOException {
    if (layout == null) {  // no layout.
      Converters.convert(this, xml);
      return;
    }
    // layout exists.
    Converters.load(this);
    while ((line = in.readLine()) != null) {
      layout.processLine(this, xml);
    }
  }
}
