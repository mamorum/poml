package poml;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

public class Dst {
  
  public StringWriter sw;
  public PrintWriter out;

  public static Dst open() {
    Dst d = new Dst();
    d.sw = new StringWriter();
    d.out= new PrintWriter(d.sw);
    return d;
  }

  public Dst load(Src src) throws IOException {
    while ((src.line = src.in.readLine()) != null) {
      process(src);
    }
    return this;
  }

  private void process(Src src) {
    int start = src.line.indexOf("{{");
    int end = src.line.indexOf("}}");
    
    // not convert
    if (start == -1 || end == -1) {
      out.println(src.line);
      return;
    }

    // convert
    String key = src.line.substring(start+2, end);
    Converters.convert(key.trim(), src, this);
    
    // convert spaces after key to new line
    if (!key.contains(" ")) return;
    char[] scan = key.toCharArray();
    for (int i = scan.length; i > 0; i--) {
      if (scan[i-1] != ' ') break;
      out.println();
    }
  }

  public Dst save(String path) {
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

  public void close() {
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
