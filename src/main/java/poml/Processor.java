package poml;

import poml.in.Poml;
import poml.out.Xml;

public class Processor {

  private static Poml poml;
  private static Xml xml;
  
  public static void start(
    String pomlPath, String xmlPath)
    throws Throwable
  {
    try {
      poml = Poml.open(pomlPath);
      xml = Xml.openBuffer();
      poml.loadConfig();
      poml.layoutTo(xml);
      xml.save(xmlPath);
    }
    finally { close(); }
  }

  private static void close() {
    try { if (xml != null) xml.closeBuffer(); }
    finally { if (poml != null) poml.close(); }
  }
}
