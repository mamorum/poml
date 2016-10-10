package poml;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

// pom.xml
public class Pom {
  
  public StringWriter sw;
  public PrintWriter out;

  public static Pom openBuffer() {
    Pom p = new Pom();
    p.sw = new StringWriter();
    p.out= new PrintWriter(p.sw);
    return p;
  }

  public Pom save(String path) {
    try (PrintWriter xml = xml(path)) {
      xml.write(sw.toString());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return this;
  }
  
  private PrintWriter xml(String path)
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
  public void printKvTags(
    String space, Map<String, String> kv
  ) {
    for (String k: kv.keySet()) {
      out.print(space);
      out.println(
        kvTagTmpl
          .replace("{{k}}", k) 
          .replace("{{v}}", kv.get(k))
      );
    }
  }
  public static final String kvTagTmpl
    = "<{{k}}>{{v}}</{{k}}>";
}
