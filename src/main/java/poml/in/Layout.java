package poml.in;

import poml.conv.Converters;
import poml.out.Xml;

public class Layout {
  
  public void processLine(Poml poml, Xml xml) {
    int start = poml.line.indexOf("{{");
    int end = poml.line.indexOf("}}");
    
    // no conversion.
    if (start == -1 || end == -1) {
      xml.out.println(poml.line);
      return;
    }

    // convert
    String key = poml.line.substring(start+2, end);
    String name = key.trim();

    preNewLine(key, name, xml);
    Converters.get(name).convert(poml, xml);
    postNewLine(key, name, xml);
  }

  private void preNewLine(String key, String name, Xml xml) {
    int count = key.indexOf(name);
    for (int i=0; i < count; i++) xml.out.println();
  }
  private void postNewLine(String key, String name, Xml xml) {
    int count = key.length() - key.indexOf(name) - name.length();
    for (int i=0; i < count; i++) xml.out.println();
  }
}
