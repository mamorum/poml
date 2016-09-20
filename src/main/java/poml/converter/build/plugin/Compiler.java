package poml.converter.build.plugin;

import java.util.Map;

import poml.Converter;
import poml.Dst;
import poml.Src;

public class Compiler extends Converter {
  
  @Override public String key() { return "compiler"; }

  private static final String startTag = 
    sp6 + "<plugin>" + nl +
      sp8 + "<groupId>org.apache.maven.plugins</groupId>" + nl + 
      sp8 + "<artifactId>maven-compiler-plugin</artifactId>";
  private static final String endTag =
    sp6 + "</plugin>";

  @Override public void convert(Src src, Dst dst) {
    appendPlugin(src, dst);
    appendConf(src, dst);
    dst.out.println(endTag);
  }
  
  private void appendPlugin(Src src, Dst dst) {
    dst.out.println(startTag);
    Map<String, String> prop = src.propMap(key());
    putDefault("version", "3.5.1", prop);
    dst.out.print(kvTags(sp8, prop));
  }

  private void appendConf(Src src, Dst dst) {
    Map<String, String> prop = src.propMap(key() + ".conf");
    if (prop.size() == 0) return;
    dst.out.println("        <configuration>");
    dst.out.print(kvTags(sp10, prop));
    dst.out.println("        </configuration>");
  }
}
