package poml;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Src {

  public BufferedReader in;
  public String line;

  public Config conf = new Config();
  public Layout layout = new Layout();
  
  public static Src open(String path) throws IOException {
    Src s = new Src();
    s.in = new BufferedReader(new InputStreamReader
      (new FileInputStream(path), "UTF-8")
    );
    return s;
  }
  
  public void toXml(Dst dst) throws IOException {
    loadConfig();
    processLayout(dst);
  }

  private void loadConfig() throws IOException {
    while ((line = in.readLine()) != null) {
      if (line.equals("---")) break;
      conf.append(line);
    }
    conf.load();
  }

  private void processLayout(Dst dst) throws IOException {
    while ((line = in.readLine()) != null) {
      layout.processLine(this, dst);
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
