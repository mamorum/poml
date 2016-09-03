package niji.lib;

import java.io.PrintWriter;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

public class Mst {

  public static MustacheFactory mf = new DefaultMustacheFactory();
  
  public static void render(
    String tmplPath, Object data, PrintWriter out
  ) {
    Mustache m = mf.compile(tmplPath);
    m.execute(out, data);
  }
}
