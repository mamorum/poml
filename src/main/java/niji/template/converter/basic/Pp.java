package niji.template.converter.basic;

import niji.template.Converter;
import niji.template.Src;
import niji.template.converter.util.Tag;

public class Pp implements Converter {

  public static String key = "pp";
  
  @Override public void toXml(Src src, StringBuilder xml) {
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
