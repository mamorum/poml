package it;

import static org.assertj.core.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

import poml.Main;

public class IntegrationTest {

  @Test public void demo() {
    test("it/demo");
  }
  @Test public void demoLayout() {
    test("it/demo-layout");
  }

  private static void test(String dir) {
    // prepare.
    String poml = dir + "/pom.poml";
    String result = dir + "/result.xml";
    String expected = dir + "/pom.xml";
    // exec.
    try { Main.main(new String[] {poml, result}); }
    catch (Throwable e) { throw new RuntimeException(e); }
    // check.
    eq(result,expected);
  }
  private static void eq(
    String path1, String path2
  ) {
    try (
      BufferedReader br1 = reader(path1);
      BufferedReader br2 = reader(path2);
    ) {
      String line1 = null;
      while ((line1 = br1.readLine()) != null) {
        assertThat(line1).isEqualTo(
          br2.readLine()
        );
      }
      assertThat(br2.readLine()).isNull();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  private static BufferedReader 
    reader(String path) throws IOException
  {
    return new BufferedReader(new InputStreamReader(
      new FileInputStream(path), "UTF-8"
    ));
  }
}
