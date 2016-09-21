package poml.converter.build.plugin;

import poml.Dst;
import poml.Src;

public class Compiler extends Plugin {
  
  @Override public String name() { return "compiler"; }

  private static final String ids =
      sp8 + "<groupId>org.apache.maven.plugins</groupId>" + nl + 
      sp8 + "<artifactId>maven-compiler-plugin</artifactId>";

  @Override public void convert(Src src, Dst dst) {
    printStart(dst);
    dst.out.println(ids);
    printVerExIn(src, dst, "3.5.1");
    printConf(src, dst);
    printEnd(dst);
  }
}
