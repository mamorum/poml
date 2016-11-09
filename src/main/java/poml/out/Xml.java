package poml.out;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Map;

// pom.xml
public class Xml {
  
  public StringBuilder out = new StringBuilder();

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

  //  -> output string, newline
  public void print(String s) {
    out.append(s);
  }
  public void print(String[] lines) {
    for (String l: lines) println(l);
  }
  public void println(String s) {
    out.append(s).append(
      System.lineSeparator()
    );
  }
  public void println() {
    out.append(System.lineSeparator());
  }
  
  // -> output tags
  public void printKvTags(
    String space, String[] k, String[] v
  ) {
    for (int i = 0; i < v.length; i++) {
      printKvTag(space, k[i], v[i]);
    }
  }
  public void printKvTags(
    String space, Map<String, String> kv
  ) {
    for (String k: kv.keySet()) {
      printKvTag(space, k, kv.get(k));
    }
  }
  public void printKvTag(
    String space, String k, String v
  ) {
    if (v == null || "".equals(v)) return;
    out.append(space);
    out.append("<").append(k).append(">");
    out.append(v);
    out.append("</").append(k).append(">");
    println();
  }
}
