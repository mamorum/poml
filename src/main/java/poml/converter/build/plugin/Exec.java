package poml.converter.build.plugin;

import poml.Dst;
import poml.Src;

public class Exec extends Plugin {
  
  @Override public String name() { return "exec"; }
  
  private static final String id =
      sp8 + "<groupId>org.codehaus.mojo</groupId>" + nl + 
      sp8 + "<artifactId>exec-maven-plugin</artifactId>";

  @Override public void convert(Src src, Dst dst) {
    printStart(dst);
    dst.out.println(id);
    printVerExIn(src, dst, "1.5.0");
    printConf(src, dst);
    printEnd(dst);
  }
}
