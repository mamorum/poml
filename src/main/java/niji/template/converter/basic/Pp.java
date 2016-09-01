package niji.template.converter.basic;

import java.util.Map;

import niji.template.Converter;
import niji.template.Src;
import niji.util.Str;

public class Pp implements Converter {

  public static String key = "pp";
  
  @Override public void toXml(Src src, StringBuilder xml) {
    Str.ln(src.indent, "<properties>", xml);
    Map<String, String> prop = src.propertyMap(key);
    for (String k: prop.keySet()) {
      String v = prop.get(k);
      Str.sp(src.indent+2, xml);
      addTag(k, v, xml);
      Str.nl(xml);
    }
    Str.ln(src.indent, "</properties>", xml);
  }

  private void addTag(
    String name, String val, StringBuilder xml
  ) {
    xml.append("<").append(name).append(">");
    xml.append(val);
    xml.append("</").append(name).append(">");
  }
}
