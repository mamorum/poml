package poml.tools.converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import poml.Converter;
import poml.Pom;

public class Tmpl {

  private static String nl = Converter.nl;
  
  public static void render(
    String path, Map<String, String> mkv,
    Pom dst
  ) {
    String str = tmpl(path);
    for (String mk: mkv.keySet()) {
      String v = mkv.get(mk);
      String k = key(mk);
      str = str.replace(k, v);
    }    
    dst.out.print(str);
  }

  private static String tmpl(String path) {
    StringBuilder sb = new StringBuilder();
    try (BufferedReader br =br(path)) {
      String line = null;
      while ((line = br.readLine()) != null) {
        sb.append(line).append(nl);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return sb.toString();
  }
  
  private static BufferedReader br(String path) {
    return  new BufferedReader(
      new InputStreamReader(
        Tmpl.class.getResourceAsStream(path)
      )
    );
  }
  
  // change map "key" to "{{key}}"
  private static String key(String mk) {
    StringBuilder sb = new StringBuilder("{{");
    return sb.append(mk).append("}}").toString();
  }
}
