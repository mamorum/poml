package poml.conv.basic;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;

public class Depends implements Converter {

  public static final Depend depend = new Depend();
  
  @Override public String name() { return "depends"; }
  
  @Override public void convert(Poml poml, Xml xml) {
    xml.out.println("  <dependencies>");
    depend.print(name(), poml, xml);
    xml.out.println("  </dependencies>");
  }
}
