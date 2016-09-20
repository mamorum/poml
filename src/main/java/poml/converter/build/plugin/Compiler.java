package poml.converter.build.plugin;

import java.util.Map;

import poml.Converter;
import poml.Dst;
import poml.Src;

public class Compiler extends Converter {
  
  @Override public String name() { return "compiler"; }

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
    Map<String, String> prop = src.propMap(name());
    putDefault("version", "3.5.1", prop);
    printKvTags(sp8, prop, dst);
  }

  private void appendConf(Src src, Dst dst) {
    Map<String, String> prop = src.propMap(name() + ".conf");
    if (prop.size() == 0) return;
    dst.out.println("        <configuration>");
    printKvTags(sp10, prop, dst);
    dst.out.println("        </configuration>");
  }
}
