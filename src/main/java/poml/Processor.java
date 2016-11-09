package poml;

import java.io.IOException;

import poml.in.Poml;
import poml.out.Xml;

public class Processor {
  
  public static void
    start(String pomlPath, String xmlPath)
  throws IOException
  {
    Poml poml = new Poml();
    Xml xml = new Xml();
    try {
      poml.open(pomlPath);
      poml.loadConfig();
      poml.to(xml);
      xml.save(xmlPath);
    }
    finally { poml.close(); }
  }
}
