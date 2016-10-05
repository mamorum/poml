package poml.converter;

import java.util.HashMap;

import poml.Converter;
import poml.converter.basic.Depend;
import poml.converter.basic.Depends;
import poml.converter.basic.Dist;
import poml.converter.basic.Property;
import poml.converter.build.plugin.Exec;
import poml.converter.build.plugin.Fatjar;
import poml.converter.build.plugin.Compiler;
import poml.converter.more.Info;
import poml.converter.project.Model4;

public class Export {

  private static Converter[] export = new Converter[] {
      // project
      new Model4.Start(),
      new Model4.End(),
      // basic
      new Dist(),
      new Depends(),
      new Depend(),
      new Property(),
      // build.plugin
      new Compiler(),
      new Fatjar(),
      new Exec(),
      // more
      new Info()
  };

  public static HashMap<String, Converter> converters() {
    HashMap<String, Converter> map = new HashMap<>();
    for (Converter c: export) map.put(c.name(), c);
    return map;
  }
}
