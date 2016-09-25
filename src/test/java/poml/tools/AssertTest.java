package poml.tools;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.Map;

import org.junit.Test;

public class AssertTest {

  @Test public void single() {
    Map<String, String> map
      = Collections.singletonMap("target", "1.8");
    try {
      Assert.exists("source", map, "javac");
    } catch (RuntimeException e) {
      assertThat(e.getMessage()).isEqualTo(
        "\"javac\" requires \"source\" in property section"
      );
    }
  }
  
  @Test public void multi() {
    Map<String, String> map
      = Collections.singletonMap("target", "1.8");
    try {
      Assert.exists(
        new String[] {"ver", "source"},
        map, "javac");
    } catch (RuntimeException e) {
      assertThat(e.getMessage()).isEqualTo(
          "\"javac\" requires \"ver\" in property section"
      );
    }
  }
}
