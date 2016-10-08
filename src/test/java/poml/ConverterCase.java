package poml;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.After;
import org.junit.Before;

public class ConverterCase {
  public Src src;
  public Dst dst;
  public Output output;
  public static String nl = System.lineSeparator();
  
  @Before public void before() {
    src = new Src();
    dst = Dst.openBuffer();
    output = new Output(dst.sw);
  }
  
  @After public void after() throws IOException {
    if (src != null) src.close();
    if (dst != null) dst.closeBuffer();
  }
  
  public class Output {
    StringWriter sw;
    Output(StringWriter sw) {this.sw = sw;}
    public void is(String s) {
      assertThat(sw.toString()).isEqualTo(s);
    }
  }
}
