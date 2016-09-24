package poml.converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import poml.Converter;

public class Tmpl {

  private static String nl = Converter.nl;
  
  public static String render(
    String path, Map<String, String> mkv
  ) {
    String str = tmpl(path);
    for (String mk: mkv.keySet()) {
      String v = mkv.get(mk);
      String k = key(mk);
      str = str.replace(k, v);
    }    
    return str;
  }
  
  private static String key(String mk) {
    StringBuilder sb = new StringBuilder("{{");
    return sb.append(mk).append("}}").toString();
  }

  public static String tmpl(String path) {
    StringBuilder sb = new StringBuilder();
    String line = null;
    try (
      BufferedReader br = new BufferedReader(
        new InputStreamReader(
          Tmpl.class.getResourceAsStream(path)
        )
      )
    ) {
      while (
        (line = br.readLine()) != null
      ) sb.append(line).append(nl);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return sb.toString();
  }
}
