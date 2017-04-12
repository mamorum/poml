package poml.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import poml.conv.Converters;

// pom.poml
public class Poml {
  
  public BufferedReader in;
  public Config conf = new Config();

  public void open(String path) throws IOException {
    in = new BufferedReader(new InputStreamReader
      (new FileInputStream(path), "UTF-8")
    );
  }
  public void close() throws IOException {
    if (in != null) in.close();
  }

  public void to(Xml xml) throws IOException {
    conf.parse(in);
    if (conf.hasLayout) (new Layout()).render(this, xml);
    else Converters.convert(this, xml);
  }
}
