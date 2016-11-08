package poml.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import poml.out.Xml;

public class Tmpl {

  private static BufferedReader reader(String path) {
    return  new BufferedReader(
      new InputStreamReader(
        Tmpl.class.getResourceAsStream(path)
      )
    );
  }

  public static void render(
    String path, Map<String, String> kv, Xml xml
  ) {
    try (BufferedReader br = reader(path)) {
      String line = null;
      while ((line = br.readLine()) != null) {
        out(line, kv, xml);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static void out(
    String line, Map<String, String> kv, Xml xml
  ) {
    int start = line.indexOf("{{");
    int end = line.lastIndexOf("}}");
    if (start == -1 || end == -1) {
      xml.println(line);
      return;
    }

    String key = line.substring(start+2, end);
    
    if (key.endsWith("+")) {
      String tag = kv.get(key);
      if (tag != null) xml.print(tag);
      return;
    }
        
    String val = kv.get(key);
    xml.print(line.substring(0, start));
    xml.print(val);
    xml.println(line.substring(end+2));
  }
}
