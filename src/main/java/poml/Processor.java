package poml;

import poml.in.Poml;
import poml.out.Xml;

public class Processor {
  
  public static void start(
    String pomlPath, String xmlPath)
    throws Throwable
  {
    Poml poml = null;
    Xml xml = null;
    try {
      poml = Poml.open(pomlPath);
      poml.loadConfig();
      xml = new Xml();
      poml.to(xml);
      xml.save(xmlPath);
    }
    finally { if (poml != null) poml.close(); }
  }
}
