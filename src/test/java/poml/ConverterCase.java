package poml;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.After;
import org.junit.Before;

import poml.in.Poml;
import poml.out.Xml;

public class ConverterCase {
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
  
  public class Output {
    StringWriter sw;
    Output(StringWriter sw) {this.sw = sw;}
    public void is(String s) {
      assertThat(sw.toString()).isEqualTo(s);
    }
  }
}
