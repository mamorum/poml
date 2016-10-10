package poml;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.After;
import org.junit.Before;

public class ConverterCase {
  public Poml poml;
  public Pom pom;
  public Output output;
  public static String nl = System.lineSeparator();
  
  @Before public void before() {
    poml = new Poml();
    pom = Pom.openBuffer();
    output = new Output(pom.sw);
  }
  
  @After public void after() throws IOException {
    if (poml != null) poml.close();
    if (pom != null) pom.closeBuffer();
  }
  
  public class Output {
    StringWriter sw;
    Output(StringWriter sw) {this.sw = sw;}
    public void is(String s) {
      assertThat(sw.toString()).isEqualTo(s);
    }
  }
}
