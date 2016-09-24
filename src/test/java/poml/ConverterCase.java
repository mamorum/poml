package poml;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.After;
import org.junit.Before;

public class ConverterCase {
  public Src src;
  public Dst dst;
  public Output output;
  public StringWriter buf;
  public static String nl = System.lineSeparator();
  
  @Before public void before() {
    src = new Src();
    dst = new Dst();
    buf = new StringWriter();
    output = new Output(buf);
    dst.out = new PrintWriter(buf);
  }
  
  @After public void after() throws IOException {
    if (src != null) src.close();
    if (dst != null) dst.close();
  }
  
  public class Output {
    StringWriter sw;
    Output(StringWriter sw) {this.sw = sw;}
    public void is(String s) {
      assertThat(sw.toString()).isEqualTo(s);
    }
  }
  public class ErrMsg {
    Exception e;
    public ErrMsg(Exception e) {this.e = e;}
    public void is(String msg) {
      assertThat(e.getMessage()).isEqualTo(msg);
    }
  }
}
