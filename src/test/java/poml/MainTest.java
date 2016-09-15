package poml;

import java.io.IOException;

import org.junit.Test;

public class MainTest {

  @Test public void test() throws IOException {
    Main.main(
      new String[]{"pom.pmol", "pom.xml"}
    );
  }

}
