package niji;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

public class Processor {

  public static String process(BufferedReader in) throws IOException {
    Properties p = processProperty(in);  // first section
    String xml = processTemplate(in, p);  // second section
    return xml;
  }
  
  public static Properties processProperty(
    BufferedReader in
  ) throws IOException {
  
    StringBuilder ptxts = new StringBuilder();
    String ptxt = null;
    while ((ptxt = in.readLine()) != null) {
      if (ptxt.equals("---")) break;
      ptxts.append(ptxt);
      ptxts.append(System.lineSeparator());
    }
    Properties p = new Properties();
    try (
      StringReader r =
        new StringReader(ptxts.toString())
    ) {
      p.load(r);
    }
    return p;
  }
  
  private static String processTemplate(
    BufferedReader in, Properties p
  ) throws IOException {

    Converter c = Converter.of(p);
    String tmpl = null;
    StringBuilder xml = new StringBuilder();
    while ((tmpl = in.readLine()) != null) {
      c.convert(tmpl, xml);
    }
    return xml.toString();
  }
}
