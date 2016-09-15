package poml;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Dst {
  
  public PrintWriter out;
  
  public static Dst open(String path) throws IOException {    
    Dst d = new Dst();
    d.out = new PrintWriter(
      new BufferedWriter(new OutputStreamWriter
        (new FileOutputStream(path), "UTF-8")
      )
    );
    return d;
  }

  public Dst from(Src src) throws IOException {
    while ((src.ctxt.line = src.in.readLine()) != null) {
      process(src);
    }
    return this;
  }

  private void process(Src src) {
    int start = src.ctxt.line.indexOf("{{");
    int end = src.ctxt.line.indexOf("}}");
    
    // no conversion
    if (start == -1 || end == -1) {
      out.println(src.ctxt.line);
      return;
    }

    // convert
    src.ctxt.indent = start;
    String key = src.ctxt.line.substring(start+2, end);
    Converters.convert(key, src, this);
  }

  public void close() {
    if (out != null) out.close();
  }
}
