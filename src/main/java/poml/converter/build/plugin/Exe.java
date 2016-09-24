package poml.converter.build.plugin;

import java.util.Map;

import poml.Converter;
import poml.Dst;
import poml.Src;
import poml.converter.Tmpl;

public class Exe extends Converter {
  
  @Override public String name() { return "exe"; }

  @Override public void convert(Src src, Dst dst) {
    Map<String, String> map = src.propMap(name());
    // TODO check mainClass.
    putDefault("ver", "1.5.0", map);
    dst.out.print(
        Tmpl.render("/tmpl/exe.tmpl", map)
      );
  }
}
