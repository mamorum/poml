package poml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import poml.io.Poml;
import poml.io.Xml;

public class UtCase {
  public static final String nl = System.lineSeparator();
  public Poml poml;
  public Xml xml;
  BufferedReader br;

  @Before public void before() {
    xml = new Xml();
  }
  @After public void after() throws IOException {
    if (this.br != null) br.close();
  }

  //-> test data
  public void poml(String poml) {
    this.br = new BufferedReader(new StringReader(poml));
    try { this.poml = Poml.of(this.br); }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  //-> assertion
  public void xml(String expected) {
    Assert.assertEquals(expected, xml.toString());
  }
  public void err(String expected, Exception e) {
    Assert.assertEquals(expected, e.getMessage());
    e.printStackTrace(System.out);
  }
}
