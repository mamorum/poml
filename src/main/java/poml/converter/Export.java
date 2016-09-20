package poml.converter;

import java.util.HashMap;

import poml.Converter;
import poml.converter.basic.Dist;
import poml.converter.basic.Lib;
import poml.converter.basic.Prop;
import poml.converter.build.plugin.Compiler;
import poml.converter.build.plugin.Exe;
import poml.converter.build.plugin.Fatjar;
import poml.converter.project.Model4;

public class Export {

  private static Converter[] export = new Converter[] {
      // project
      new Model4.Start(),
      new Model4.End(),
      // basic
      new Dist(),
      new Lib(),
      new Prop(),
      // build.plugin
      new Compiler(),
      new Fatjar(),
      new Exe()
  };

  public static HashMap<String, Converter> converters() {
    HashMap<String, Converter> map = new HashMap<>();
    for (Converter c: export) map.put(c.key(), c);
    return map;
  }
}
