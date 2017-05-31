package poml.io;

import java.util.Map;

// pom.xml
public class Xml {
  public static final String  // indent
    sp2="  ", sp4="    ", sp6="      ",
    sp8="        ", sp10="          ";

  private StringBuilder xml = new StringBuilder();

  // -> api to add xml element.
  public Xml txt(String s) {
    xml.append(s);
    return this;
  }
  public void nl() {
    xml.append(System.lineSeparator());
  }
  public void line(String l) {
    xml.append(l); nl();
  }

  public void tags(
    String space, String[] key, Map<String, String> val
  ) {
    for (String k: key) tag(space, k, val.get(k));
  }
  public void tags(
    String space, String[] key, String[] val
  ) {
    for (int i = 0; i < val.length; i++) {
      tag(space, key[i], val[i]);
    }
  }
  public void tag(
    String space, String key, String val
  ) {
    if (val == null || "".equals(val)) return;
    xml.append(space);
    xml.append("<").append(key).append(">");
    xml.append(val);
    xml.append("</").append(key).append(">");
    nl();
  }

  // -> return xml content
  @Override public String toString() {
    return xml.toString();
  }
}