package poml.in;

import poml.conv.Converters;
import poml.out.Xml;

public class Layout {

  public void processLine(Poml poml, Xml xml) {
    int start = poml.line.indexOf("{{");
    int end = poml.line.indexOf("}}");

    if (start == -1 || end == -1) {
      xml.println(poml.line);
      return;  // not convert
    }

    // ex. "{{ pkg  }}" -> key=" pkg  " -> name="pkg"
    String key = poml.line.substring(start+2, end);
    String name = key.trim();

    preNewline(key, name, xml);
    Converters.get(name).convert(poml, xml);
    postNewline(key, name, xml);
  }

  // ex. " pkg  " -> print newline * 1
  private void preNewline(String key, String name, Xml xml) {
    int count = key.indexOf(name);
    for (int i=0; i < count; i++) xml.println();
  }
  // ex. " pkg  " -> print newline * 2 
  private void postNewline(String key, String name, Xml xml) {
    int count = key.length() - key.indexOf(name) - name.length();
    for (int i=0; i < count; i++) xml.println();
  }
}
