package poml.io;

import java.util.Map;

// pom.xml
public class Xml {
  public static final String
    sp2="  ", sp4="    ", sp6="      ",
    sp8="        ", sp10="          ", // <-indent
    nl=System.lineSeparator();
  private static final int nlLen=nl.length();

  private StringBuilder xml = new StringBuilder();

  // -> api to add xml element.
  public Xml txt(String s) {
    xml.append(s);
    return this;
  }
  public void nl() {
    xml.append(nl);
  }
  public void line(String l) {
    xml.append(l).append(nl);
  }
  public void xml(String space, String x) {
    for (int s=0, e=0;;s = e) {
      e = x.indexOf(nl, s);
      if (e == -1) break;
      e = e+nlLen;
      xml.append(space).append(
        x.substring(s, e)
      );
    }
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