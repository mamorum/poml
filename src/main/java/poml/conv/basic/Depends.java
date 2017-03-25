package poml.conv.basic;

import poml.conv.Converter;
import poml.io.Poml;
import poml.io.Xml;

public class Depends implements Converter {

  public static final Depend depend = new Depend();
  
  @Override public String name() { return "depends"; }
  
  @Override public void convert(Poml poml, Xml xml) {
    xml.out.add("  <dependencies>").nl();
    depend.converts(name(), poml, xml);
    xml.out.add("  </dependencies>").nl();
  }
}
