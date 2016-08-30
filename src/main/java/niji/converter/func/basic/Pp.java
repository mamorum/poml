package niji.converter.func.basic;

import niji.Converter.Func;
import niji.converter.Src;
import niji.converter.util.Tag;

public class Pp implements Func {

  public static String key = "pp";
  
  @Override public void accept(Src src, StringBuilder xml) {
    String ppv = src.p.getProperty(key);
    Tag tag = Tag.init(src.indent, "<properties>", "</properties>");
    
    for (String kv: ppv.split(",")) {
      addLine(src.indent, kv, tag);
    }
    xml.append(tag.string());
  }
  
  public void addLine(int depth, String kv, Tag t) {
    String[] kva = kv.split(":");
    String key = kva[0].trim();
    String val = kva[1].trim();
    t.line(tag(key, val));
  }

  private String tag(String key, String val) {
    StringBuilder sb = new StringBuilder();
    sb.append("<").append(key).append(">");
    sb.append(val);
    sb.append("</").append(key).append(">");
    return sb.toString();
  }
}
