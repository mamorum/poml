package poml.io;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

// pom.xml
public class Xml {
  
  public Txt out = Txt.init();

  public void save(String path) throws IOException {
    try (PrintWriter file = file(path)) {
      file.write(out.toString());
    }
  }  
  private PrintWriter file(String path) throws IOException {
    return new PrintWriter(
      new BufferedWriter(new OutputStreamWriter(
        new FileOutputStream(path), "UTF-8")
      )
    );
  }

  // -> output tags
  public void outTags(
    String space, String[] key, String[] val
  ) {
    for (int i = 0; i < val.length; i++) {
      outTag(space, key[i], val[i]);
    }
  }
  public void outTag(
    String space, String key, String val
  ) {
    if (val == null || "".equals(val)) return;
    out.add(space);
    out.add("<").add(key).add(">");
    out.add(val);
    out.add("</").add(key).add(">");
    out.nl();
  }
}