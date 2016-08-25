package niji;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Properties;

public class Main {

  private static BufferedReader reader() throws IOException {
    return new BufferedReader(
        new InputStreamReader(
            Main.class.getResourceAsStream("/pom.nj"),
            "UTF-8"
        )
    );
  }
  
  static String line = null;
  static long start = System.currentTimeMillis();
  
  static Properties properties = null;
  private static class Property {
    Properties pp = new Properties();
    StringBuilder sb = new StringBuilder();
    public void add(String line) {
      sb.append(line);
      sb.append(System.lineSeparator());
    }
    public Properties build() throws IOException {
      try (StringReader sr = new StringReader(sb.toString())) {
        this.pp.load(sr);
        return this.pp;
      }
    }
  }
  
  public static void main(String[] args) throws IOException {
    try (BufferedReader br = reader()) {
      // first section
      Property property = new Property();
      while ((line = br.readLine()) != null) {
        if (line.equals("---")) break;
        property.add(line);
      }
      properties = property.build();
      
      // second section
      while ((line = br.readLine()) != null) {
        processTmpl();
      }
      finish();
    }
  }
  
  
  static StringBuilder xml = new StringBuilder();
  
  private static void processTmpl() throws IOException {
    int start = line.indexOf("{{");
    int end = line.indexOf("}}");
    if (start == -1 || end == -1) {
      appendOnly();
      return;
    }
    String key = line.substring(start+2, end);
    String mark = line.substring(start, end+2);
    String replaced = line.replace(
        mark, Converter.tags(key, properties)
    );
    xml.append(replaced);      
  }
  
  private static void appendOnly() {
    xml.append(line);
    xml.append(System.lineSeparator());
  }

  private static void finish() {
    // TODO output pom.xml.
    System.out.println(xml.toString());
    System.out.println(
        "Finished " +
        (System.currentTimeMillis() - start) +
        "msec."
    );
  }
}
