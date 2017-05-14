package poml.out;

// pom.xml
public class Xml {
  private StringBuilder out = new StringBuilder();

  // -> api for adding element to xml.
  public Xml txt(String s) {
    out.append(s);
    return this;
  }
  public void nl() {
    out.append(System.lineSeparator());
  }
  public void line(String l) {
    out.append(l); nl();
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
    out.append(space);
    out.append("<").append(key).append(">");
    out.append(val);
    out.append("</").append(key).append(">");
    nl();
  }

  // -> return xml content
  @Override public String toString() {
    return out.toString();
  }
}