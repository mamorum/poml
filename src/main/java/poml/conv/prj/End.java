package poml.conv.prj;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;

public class End implements Converter {
  @Override public String name() { return "end"; }
  @Override public void convert(Poml in, Xml out) {
    out.txt("</project>");
  }
}
