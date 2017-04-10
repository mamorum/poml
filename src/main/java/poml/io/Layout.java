package poml.io;

import java.io.IOException;

import poml.conv.Converters;

public class Layout {

  private String line;

  public void render(Poml poml, Xml xml)  throws IOException {
    Converters.load(poml);
    while ((line = poml.in.readLine()) != null) {
      convert(poml, xml);
    }
  }  

  private void convert(Poml poml, Xml xml) {
    int start = line.indexOf("{{");
    int end = line.indexOf("}}");

    if (start == -1 || end == -1) {
      xml.out.add(line).nl();
      return;  // not convert
    }

    // ex. "{{ pkg  }}" -> key=" pkg  " -> name="pkg"
    String key = line.substring(start+2, end);
    String name = key.trim();

    preNewline(key, name, xml);
    Converters.convert(name, poml, xml);
    postNewline(key, name, xml);
  }
  private void preNewline(String key, String name, Xml xml) {
    // ex. " pkg  " -> print newline * 1
    int count = key.indexOf(name);
    for (int i=0; i < count; i++) xml.out.nl();
  } 
  private void postNewline(String key, String name, Xml xml) {
    // ex. " pkg  " -> print newline * 2
    int count = key.length() - key.indexOf(name) - name.length();
    for (int i=0; i < count; i++) xml.out.nl();
  }
}
