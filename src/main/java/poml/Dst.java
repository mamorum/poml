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

  public void close() {
    if (out != null) out.close();
  }
}
