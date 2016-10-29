package poml.tool.converter;

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
      xml.out.println(line);
      return;
    }

    String key = line.substring(start+2, end);
    
    if (key.endsWith("+")) {
      String tag = kv.get(key);
      if (tag != null) xml.out.print(tag);
      return;
    }
        
    String val = val(key, kv);
    xml.out.print(line.substring(0, start));
    xml.out.print(val);
    xml.out.println(line.substring(end+2));
  }
  
  private static String val(String key, Map<String, String> kv) {
    int colon = key.indexOf(":");
    if (colon == -1) return kv.get(key);  // no default val.
    String val = kv.get(key.substring(0, colon));  // using new key.
    if (val == null) return key.substring(colon+1);  // default.
    else return val; // map val. ( user setting val. )
  }
}
