package niji.converter.util;

public class Tag {

  static final String ls = System.lineSeparator();
  StringBuilder sb;
  String last;
  
  public Tag() {this.sb = new StringBuilder();}
  
  public static Tag init() {
    Tag t = new Tag();
    return t;
  }
  
  public static Tag init(String first, String last) {
    Tag t = init();
    t.line(first);
    t.last = last;
    return t;
  }
  
  public Tag line(String line) {
    sb.append(line).append(ls);
    return this;
  }
  
  public Tag child(Tag t) {
    sb.append(t.string());
    return this;
  }
  
  public String string() {
    if (last != null) line(last);
    return sb.toString();
  }
}
