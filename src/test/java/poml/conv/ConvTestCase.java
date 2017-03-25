package poml.conv;

import static org.assertj.core.api.Assertions.assertThat;

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
      assertThat(this.msg).startsWith(prefix);
    }
  }
  
  public class Output {
    public void is(String s) {
      assertThat(xml.out.toString()).isEqualTo(s);
    }
  }
}
