package poml;

public class Layout {
  
  public void processLine(Src src, Dst dst) {
    int start = src.line.indexOf("{{");
    int end = src.line.indexOf("}}");
    
    // not convert
    if (start == -1 || end == -1) {
      dst.out.println(src.line);
      return;
    }

    // convert
    String key = src.line.substring(start+2, end);
    Converters.convert(key.trim(), src, dst);
    
    // convert spaces after key to new line
    if (!key.contains(" ")) return;
    char[] scan = key.toCharArray();
    for (int i = scan.length; i > 0; i--) {
      if (scan[i-1] != ' ') break;
      dst.out.println();
    }
  }
}
