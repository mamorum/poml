package niji.converter.build.plugin;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import niji.Converters.Converter;
import niji.Dst;
import niji.Src;

public class Compile implements Converter {
  
  public static String key = "compile";
  
  @Override public void convert(Src src, Dst dst) {
    MustacheFactory mf = new DefaultMustacheFactory();
    Mustache m = mf.compile("compile.mustache");
    m.execute(
      dst.out, src.pMap(key)
    );
    
//    int indent = src.indent;
//    Str.ln(indent, "<plugin>", dst);
//    Str.ln(indent+2, "<groupId>org.apache.maven.plugins</groupId>", dst);
//    Str.ln(indent+2, "<artifactId>maven-compiler-plugin</artifactId>", dst);
//    Str.ln(indent+2, "<version>3.5.1</version>", dst);
//    Str.ln(indent+2, "<configuration>", dst);
//    addConf("source",prop.get("source"), indent+4, dst);
//    addConf("target",prop.get("target"), indent+4, dst);
//    Str.ln(indent+2, "</configuration>", dst);
//    Str.ln(indent, "</plugin>", dst);
  }
  
//  public void addConf(
//    String tag, String value,
//    int indent, StringBuilder xml
//  ) {
//    Str.sp(indent, xml);
//    xml.append("<").append(tag).append(">");
//    xml.append(value);
//    xml.append("</").append(tag).append(">");
//    Str.nl(xml);
//  }
}
