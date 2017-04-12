package poml.conv;

import java.io.BufferedReader;
import java.io.StringReader;

import org.junit.Assert;
import org.junit.Before;

import poml.io.Poml;
import poml.io.Xml;

public class ConvTestCase {
  public Poml poml;
  public Xml xml;
  public Output output;
  public static String nl = System.lineSeparator();
  
  @Before public void before() {
    poml = new Poml();
    xml = new Xml();
    output = new Output();
  }

  // Create test input.
  protected BufferedReader in(String... lines) {
    StringBuilder sb = new StringBuilder();
    for (String l: lines) {
      sb.append(l).append(System.lineSeparator());
    }
    return new BufferedReader(
      new StringReader(sb.toString())
    );
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
  
  public class Output {
    public void is(String s) {
      Assert.assertEquals(s, xml.out.toString());
    }
  }
}
