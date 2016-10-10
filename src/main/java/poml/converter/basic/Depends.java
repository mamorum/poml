package poml.converter.basic;

import poml.Pom;
import poml.Poml;

public class Depends extends Depend {

  @Override public String name() { return "depends"; }
  
  @Override public void convert(Poml poml, Pom pom) {
    pom.out.println(sp2 + "<dependencies>");
    super.convert(poml, pom);
    pom.out.println(sp2 + "</dependencies>");
  }
}
