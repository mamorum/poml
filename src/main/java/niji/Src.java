package niji;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class Src {

  public BufferedReader in;
  public Properties p = new Properties();
  public Context c = new Context();
  
  public class Context {
    public String line;
    public int indent;
  }
  
  public static Src open(String path) throws IOException {
    Src s = new Src();
    s.in = new BufferedReader(new InputStreamReader
      (new FileInputStream(path), "UTF-8")
    );
    return s;
  }
  
  public Src loadProperties() throws IOException {
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
      p.load(r);
    }
    return this;
  }
  
  public String p(String key) {
    return p.getProperty(key);
  }
  public String[] pList(String key) {
    String pp = p(key);
    if (pp == null) return null;
    else return pp.split(",");
  }
  public Map<String, String> pMap(String key) {
    Map<String, String> map = new LinkedHashMap<>();
    for (String pp :pList(key)) {
      String[] kv = pp.split(":");
      String k = kv[0].trim();
      String v = kv[1].trim();
      map.put(k, v);
    }
    return map;
  }

  public void close() throws IOException {
    if (in != null) in.close();
  }
}
