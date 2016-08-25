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
  
  static Properties properties = new Properties();
  
  public static void buildProperties(String in) throws IOException {
     try (StringReader sr = new StringReader(in)) {
       properties.load(sr);
    } catch (IOException e) {
      throw e;
    }
  }
  
  public static void main(String[] args)  {

    long start = System.currentTimeMillis();
    
    try (BufferedReader br = reader()) {
      
      StringBuilder
          pp = new StringBuilder(),
          xml = new StringBuilder(); 
      String line = null;
      boolean firstSection = true;
      
      while ((line = br.readLine()) != null) {
        if ("---".equals(line)) {
          buildProperties(pp.toString());
          firstSection = false;
          continue;
        }
        // process properties.
        if (firstSection) {
          pp.append(line + System.lineSeparator());
          continue;
        }
        // process template.
        if (line.contains("{{")) {
          if (line.contains("dist}}")) {
            String replaced = line.replace(
                "{{dist}}", Converter.tags("dist", properties)
            );
            xml.append(replaced);
            continue;
          }
          if (line.contains("lib}}")) {
            String replaced = line.replace(
                "{{lib}}", Converter.tags("lib", properties)
            );
            xml.append(replaced);
            continue;
          }
        }
        // read tmpl and process properties.      
        xml.append(line).append(System.lineSeparator());
      }
      
      System.out.println(xml.toString());
      System.out.println(
          "Finesed " +
          (System.currentTimeMillis() - start) +
          "msec."
      );
      
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
