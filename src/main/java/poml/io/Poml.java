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
  
  private class Layout {
    String line;
    public void render(Poml poml, Xml xml)  throws IOException {
      while ((line = poml.in.readLine()) != null) {
        if (line.length() == 0) xml.out.nl();
        else convert(poml, xml);
      }
    }
    private void convert(Poml poml, Xml xml) {
      boolean endsAmp = line.endsWith("&");    
      int start = line.indexOf("{{");
      int end = line.indexOf("}}");
      if (start == -1 || end == -1) { // not convert
        if (endsAmp) line = line.substring(0, line.length()-1);
        xml.out.add(line).nl();
      } else {  // convert
        String name = line.substring(start+2, end);
        Converters.convert(name, poml, xml);
      }
      if (endsAmp) xml.out.nl();
    }
  }
}
