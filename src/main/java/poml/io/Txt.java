package poml.io;

public class Txt {
  private StringBuilder b;
  private Txt(StringBuilder b) { this.b = b; }
  public static Txt init() { return new Txt(new StringBuilder()); }

  public Txt add(String word) {
    b.append(word);
    return this;
  }
  public Txt add(String[] lines) {
    for (String l: lines) add(l).nl();
    return this;
  }
  private static final String nl = System.lineSeparator();
  public Txt nl() {
    b.append(nl);
    return this;
  }
  
  @Override public String toString() {
    return b.toString();
  }
}
