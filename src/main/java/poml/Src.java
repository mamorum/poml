package poml;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

public class Src {

  public BufferedReader in;
  public String line;
  public Config conf;
  
  public static Src open(String path) throws IOException {
    Src s = new Src();
    s.conf = new Config();
    s.in = new BufferedReader(new InputStreamReader
      (new FileInputStream(path), "UTF-8")
    );
    return s;
  }
  
  public Src loadConfig() throws IOException {
    String line = null;
    StringBuilder txt = new StringBuilder();
    while ((line = in.readLine()) != null) {
      if (line.equals("---")) break;
      txt.append(line);
      txt.append(System.lineSeparator());
    }
    try (
      StringReader r
        = new StringReader(txt.toString())
    ) {
      conf.p.load(r);
    }
    return this;
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
