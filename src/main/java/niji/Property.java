package niji;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

import niji.util.Str;

public class Property {

  public static Properties process(
      BufferedReader in
  ) throws IOException {

    String line = null;
    StringBuilder pp = new StringBuilder();
    while ((line = in.readLine()) != null) {
      if (line.equals("---")) break;
      Str.ln(line, pp);
    }
    Properties p = new Properties();
    try (
      StringReader r =
        new StringReader(pp.toString())
    ) {
      p.load(r);
    }
    return p;
  }

}
