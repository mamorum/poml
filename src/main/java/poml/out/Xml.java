package poml.out;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

// pom.xml
public class Xml {
  private StringBuilder out = new StringBuilder();

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

  // -> output api
  public Xml nl() {
    out.append(System.lineSeparator());
    return this;
  }
  public Xml txt(String s) {
    out.append(s);
    return this;
  }
  public void line(String l) {
    out.append(l);
    nl();
  }
  public void tags(
    String space, String[] key, String[] val
  ) {
    for (int i = 0; i < val.length; i++) {
      tag(space, key[i], val[i]);
    }
  }
  public void tag(
    String space, String key, String val
  ) {
    if (val == null || "".equals(val)) return;
    out.append(space);
    out.append("<").append(key).append(">");
    out.append(val);
    out.append("</").append(key).append(">");
    nl();
  }

  // -> show xml
  @Override public String toString() {
    return out.toString();
  }
}
