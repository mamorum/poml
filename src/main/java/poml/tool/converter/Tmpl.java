package poml.tool.converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import poml.Converter;

public class Tmpl {

  private static String nl = Converter.nl;
  
  // change map "key" to "{{key}}"
  private static String key(String mk) {
    StringBuilder sb = new StringBuilder("{{");
    return sb.append(mk).append("}}").toString();
  }

  public static String render(
    String path, Map<String, String> mkv
  ) {
    String tmpl = text(path);
    for (String mk: mkv.keySet()) {
      String v = mkv.get(mk);
      String k = key(mk);
      tmpl = tmpl.replace(k, v);
    }    
    return tmpl;
  }

  public static String text(String path) {
    StringBuilder sb = new StringBuilder();
    try (BufferedReader br = Reader.from(path)) {
      String line = null;
      while ((line = br.readLine()) != null) {
        sb.append(line).append(nl);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return sb.toString();
  }
  
  public static class Reader {
    public static BufferedReader from(String path) {
      return  new BufferedReader(
        new InputStreamReader(
          Tmpl.class.getResourceAsStream(path)
        )
      );
    }
  }
}
