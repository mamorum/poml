package niji;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

public class Property {

  public static Properties process(
      BufferedReader in
  ) throws IOException {
    
    StringBuilder txt = new StringBuilder();
    String line = null;
    while ((line = in.readLine()) != null) {
      if (line.equals("---")) break;
      txt.append(line);
      txt.append(System.lineSeparator());
    }
    Properties p = new Properties();
    try (
      StringReader r =
        new StringReader(txt.toString())
    ) {
      p.load(r);
    }
    return p;
  }

}
