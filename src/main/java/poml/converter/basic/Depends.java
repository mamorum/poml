package poml.converter.basic;

import poml.Xml;
import poml.Poml;

public class Depends extends Depend {

  @Override public String name() { return "depends"; }
  
  @Override public void convert(Poml poml, Xml xml) {
    xml.out.println(sp2 + "<dependencies>");
    super.convert(poml, xml);
    xml.out.println(sp2 + "</dependencies>");
  }
}
