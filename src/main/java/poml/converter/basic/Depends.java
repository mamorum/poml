package poml.converter.basic;

import poml.Dst;
import poml.Src;

public class Depends extends Depend {

  @Override public String name() { return "depends"; }
  
  @Override public void convert(Src src, Dst dst) {
    dst.out.println(sp2 + "<dependencies>");
    super.convert(src, dst);
    dst.out.println(sp2 + "</dependencies>");
  }
}
