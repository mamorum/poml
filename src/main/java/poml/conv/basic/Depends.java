package poml.conv.basic;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;

public class Depends implements Converter {

  public static final Depend depend = new Depend();
  
  @Override public String name() { return "depends"; }
  
  @Override public void convert(Poml poml, Xml xml) {
    xml.println("  <dependencies>");
    depend.converts(name(), poml, xml);
    xml.println("  </dependencies>");
  }
}
