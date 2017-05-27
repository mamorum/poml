package poml.conv;

import java.io.BufferedReader;
import java.io.StringReader;

import org.junit.Assert;
import org.junit.Before;

import poml.Poml;
import poml.Xml;

public class ConvTestCase {
  public Poml poml;
  public Xml xml;
  public static String nl = System.lineSeparator();

  @Before public void before() {
    xml = new Xml();
  }

  public BufferedReader data(String lines) {
    return new BufferedReader(
      new StringReader(lines)
    );
  }
  public void result(String expected) {
    Assert.assertEquals(expected, xml.toString());
  }
  public Msg msg(Exception e) {
    return new Msg(e.getMessage());
  }

  public class Msg {
    String msg;
    Msg(String msg) { this.msg=msg; }
    public void starts(String prefix) {
      starts(prefix, false);
    }
    public void starts(String prefix, boolean debug) {
      if (debug) System.out.println(this.msg);
      Assert.assertTrue(this.msg.startsWith(prefix));
    }
  }
}
