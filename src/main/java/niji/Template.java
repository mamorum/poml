package niji;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Properties;

import niji.template.Converter;
import niji.template.Converters;
import niji.template.Src;

public class Template {

  public static String process(
    BufferedReader in, Properties p
  ) throws IOException {

    String line = null;
    StringBuilder xml = new StringBuilder();
    while ((line = in.readLine()) != null) {
      processLine(line, p, xml);
    }
    return xml.toString();
  }

  private static void processLine(
      String line, Properties p, StringBuilder xml
  ) {
    int start = line.indexOf("{{");
    int end = line.indexOf("}}");
    
    // no conversion
    if (start == -1 || end == -1) {
      xml.append(line);
      xml.append(System.lineSeparator());
      return;
    }
    
    // convert
    String key = line.substring(start+2, end);
    Converter converter = Converters.get(key);
    Src src = Src.of(p, start);
    converter.toXml(src, xml);
  }
}
