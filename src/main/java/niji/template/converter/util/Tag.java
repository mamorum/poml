package niji.template.converter.util;

public class Tag {

  static final String ls = System.lineSeparator();
  static final int childDepth = 2; 
  StringBuilder sb;
  String fisrt, last;
  int depth;  
  
  private Tag(int depth) {
    this.sb = new StringBuilder();
    this.depth = depth;
  }
  
  public static Tag init(int depth) {
    Tag t = new Tag(depth);
    return t;
  }
  
  public static Tag init(int depth, String first, String last) {
    Tag t = init(depth);
    t.line(first);
    t.fisrt = first;
    t.last = last;
    return t;
  }
  
  public Tag line(String line) {
    if (last == null) padding(depth);
    else padding(depth + childDepth);
    sb.append(line).append(ls);
    return this;
  }
  
  private void padding(int depth) {
    for (int i = 0; i < depth; i++) sb.append(" ");
  }

  public Tag child(Tag t) {
    sb.append(t.string());
    return this;
  }
  
  public String string() {
    if (last != null) {
      padding(depth);
      sb.append(last).append(ls);
    }
    return sb.toString();
  }
}
