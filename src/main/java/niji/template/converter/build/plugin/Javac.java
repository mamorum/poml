package niji.template.converter.build.plugin;

import java.util.Map;

import niji.template.Converter;
import niji.template.Src;
import niji.util.Str;

public class Javac implements Converter {
  
  public static String key = "javac";
  
  @Override public void toXml(Src src, StringBuilder xml) {
    Map<String, String> prop = src.propertyMap(key);
    int indent = src.indent;
    Str.ln(indent, "<plugin>", xml);
    Str.ln(indent+2, "<groupId>org.apache.maven.plugins</groupId>", xml);
    Str.ln(indent+2, "<artifactId>maven-compiler-plugin</artifactId>", xml);
    Str.ln(indent+2, "<version>3.5.1</version>", xml);
    Str.ln(indent+2, "<configuration>", xml);
    addConf("source",prop.get("source"), indent+4, xml);
    addConf("target",prop.get("target"), indent+4, xml);
    Str.ln(indent+2, "</configuration>", xml);
    Str.ln(indent, "</plugin>", xml);
  }
  
  public void addConf(
    String tag, String value,
    int indent, StringBuilder xml
  ) {
    Str.sp(indent, xml);
    xml.append("<").append(tag).append(">");
    xml.append(value);
    xml.append("</").append(tag).append(">");
    Str.nl(xml);
  }
}
