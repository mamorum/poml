package poml.io;

import poml.Throw;

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
  public void kvs(String space, String[] kvs, String confKey) {
    for (String kv: kvs) kv(space, kv, confKey);
  }
  public void kv(String space, String kv, String confKey) {
    int gt = kv.indexOf('>');
    if (gt == -1) Throw.val(confKey, kv);
    String k = kv.substring(0, gt);
    xml.append(space).append(
      "<").append(kv).append("</").append(k).append(">"
    ).append(nl);
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