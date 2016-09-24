package poml.converter.build.plugin;

import java.util.Map;

import poml.Converter;
import poml.Dst;
import poml.Src;
import poml.converter.Tmpl;

public class Javac extends Converter {
  
  @Override public String name() { return "javac"; }

  @Override public void convert(Src src, Dst dst) {
    Map<String, String> map = src.propMap(name());
    // TODO check source, target.
    putDefault("ver", "3.5.1", map);
    dst.out.print(
        Tmpl.render("/tmpl/javac.tmpl", map)
      );
  }
}
