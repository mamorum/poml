package poml.convert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.Assert;
import org.junit.Before;

import poml.io.Poml;
import poml.io.Xml;

public class TestCase {
  public Poml poml;
  public Xml xml;
  public static String nl = System.lineSeparator();

  @Before public void before() {
    xml = new Xml();
  }

  public void poml(String lines) {
    try {
      this.poml = Poml.of(
        new BufferedReader(new StringReader(lines)
      ));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  public void xml(String expected) {
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
