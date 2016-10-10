package poml;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

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

  public void layoutTo(Pom pom) throws IOException {
    while ((line = in.readLine()) != null) {
      layout.processLine(this, pom);
    }
  }

  public void close() {
    try {
      if (in != null) in.close();
    } catch (IOException e) {
      // Ignore, because ...
      // - no idea to recover.
      // - xml is generated.
      // - this process ends soon.
    }
  }
}
