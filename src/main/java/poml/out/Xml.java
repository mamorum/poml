package poml.out;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

// pom.xml
public class Xml {
  
  public StringWriter sw;
  public PrintWriter out;

  public static Xml openBuffer() {
    Xml x = new Xml();
    x.sw = new StringWriter();
    x.out= new PrintWriter(x.sw);
    return x;
  }

  public void save(String path) {
    try (PrintWriter file = file(path)) {
      file.write(sw.toString());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  private PrintWriter file(String path)
    throws IOException
  {
    return  new PrintWriter(
      new BufferedWriter(new OutputStreamWriter
        (new FileOutputStream(path), "UTF-8")
      )
    );
  }

  public void closeBuffer() {
    if (out != null) out.close();
  }

  // output methods ->
  public void print(String[] lines) {
    for (String l: lines) out.println(l);
  }
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
    out.print(space);
    out.print("<"); out.print(k); out.print(">");
    out.print(v);
    out.print("</"); out.print(k); out.println(">");
  }
}
