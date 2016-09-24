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
        "[ERROR] \"source\" is required in \"javac\" property."
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
        "[ERROR] \"ver\" is required in \"javac\" property."
      );
    }
  }
}
