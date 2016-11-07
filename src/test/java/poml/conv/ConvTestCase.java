package poml.conv;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.After;
import org.junit.Before;

import poml.in.Poml;
import poml.out.Xml;

public class ConvTestCase {
  public Poml poml;
  public Xml xml;
  public Output output;
  public static String nl = System.lineSeparator();
  
  @Before public void before() {
    poml = new Poml();
    xml = Xml.openBuffer();
    output = new Output(xml.sw);
  }
  
  @After public void after() throws IOException {
    if (poml != null) poml.close();
    if (xml != null) xml.closeBuffer();
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
    StringWriter sw;
    Output(StringWriter sw) {this.sw = sw;}
    public void is(String s) {
      assertThat(sw.toString()).isEqualTo(s);
    }
  }
}
