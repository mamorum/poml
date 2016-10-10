package poml;

public class Layout {
  
  public void processLine(Poml poml, Xml xml) {
    int start = poml.line.indexOf("{{");
    int end = poml.line.indexOf("}}");
    
    // not convert
    if (start == -1 || end == -1) {
      xml.out.println(poml.line);
      return;
    }

    // convert
    String key = poml.line.substring(start+2, end);
    Converters.convert(key.trim(), poml, xml);

    // convert spaces after key to new line
    if (!key.contains(" ")) return;
    char[] scan = key.toCharArray();
    for (int i = scan.length; i > 0; i--) {
      if (scan[i-1] != ' ') break;
      xml.out.println();
    }
  }
}
