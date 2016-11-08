package it;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

import poml.Main;

public class IntegrationTest {

  @Test public void demo() {
    start("example/demo");
  }
  @Test public void demoLayout() {
    start("example/demo-layout");
  }
  @Test public void all() {
    start("example/all");
  }
  @Test public void allLayout() {
    start("example/all-layout");
  }

  private static void start(String dir) {
    // prepare.
    String poml = dir + "/pom.poml";
    String converted = dir + "/tmp.xml";
    String expected = dir + "/pom.xml";
    // exec.
    try { Main.main(new String[] {poml, converted}); }
    catch (Throwable e) { throw new RuntimeException(e); }
    // check.
    try { eq(converted, expected); }
    catch (IOException e) { throw new RuntimeException(e);}
    finally { (new File(converted)).delete(); }
  }
  private static void eq(
    String path1, String path2
  ) throws IOException {
    try (
      BufferedReader br1 = reader(path1);
      BufferedReader br2 = reader(path2);
    ) {
      StringBuilder sb1 = new StringBuilder();
      StringBuilder sb2 = new StringBuilder();
      read(br1, sb1);
      read(br2, sb2);
      assertThat(sb1.toString()).isEqualTo(sb2.toString());
    }
  }
  private static BufferedReader 
    reader(String path) throws IOException
  {
    return new BufferedReader(new InputStreamReader(
      new FileInputStream(path), "UTF-8"
    ));
  }
  private static void read(
    BufferedReader from, StringBuilder to) throws IOException
  {
    String line = null;
    while ((line = from.readLine()) != null) {
      to.append(line);
      to.append(System.lineSeparator());
    }
  }
}
