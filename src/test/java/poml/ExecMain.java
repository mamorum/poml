package poml;

import java.io.IOException;

public class ExecMain {

  public static void main(String[] args) throws IOException {
    Main.main(
      new String[]{"pom.poml", "pom.xml"}
    );
  }

}
