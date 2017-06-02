package poml.convert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import poml.io.Poml;
import poml.io.Xml;

public class TestCase {
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

  public void poml(String poml) {
    this.br = new BufferedReader(new StringReader(poml));
    try { this.poml = Poml.of(this.br); }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  public void xml(String expected) {
    Assert.assertEquals(expected, xml.toString());
  }
  public void msg(String expected) {
    Assert.assertEquals(expected, xml.toString());
  }

  // TODO-> delete
  public Msg msg(Exception e) {
    return new Msg(e.getMessage());
  }
  public class Msg {
    String msg;
    Msg(String msg) { this.msg=msg; }
    public void is(String expected) {
      System.out.println(this.msg);
      Assert.assertEquals(expected, this.msg);
    }

    // TODO delete ->
    public void starts(String prefix) {
      starts(prefix, false);
    }
    public void starts(String prefix, boolean debug) {
      if (debug) System.out.println(this.msg);
      Assert.assertTrue(this.msg.startsWith(prefix));
    }
  }
}
