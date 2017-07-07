package poml.io;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class XmlTest {
  private static final String nl = System.lineSeparator();

  Xml xml;
  @Before public void ini() { this.xml = new Xml(); }

  @Test public void txt() {
    xml.txt("txt");
    assertEquals("txt", xml.toString());
  }
  @Test public void nl() {
    xml.nl();
    assertEquals(nl, xml.toString());
  }
  @Test public void line() {
    xml.line("line");
    assertEquals(
      "line" + nl,
      xml.toString()
    );
  }

  //-> tags
  @Test public void tags_same_length() {
    xml.tags("  ",
      new String[] {"k1", "k2"},
      new String[] {"v1", "v2"}
    );
    assertEquals(
      "  <k1>v1</k1>" + nl +
      "  <k2>v2</k2>" + nl,
      xml.toString()
    );
  }
  @Test public void tags_not_same_length() {
    xml.tags("  ",
      new String[] {"k1", "k2"},
      new String[] {"v1"}
    );
    assertEquals(
      "  <k1>v1</k1>" + nl,
      xml.toString()
    );
  }
  @Test public void tags_val_empty() {
    xml.tags("  ",
      new String[] {"k1", "k2"},
      new String[] {"v1", ""}
    );
    assertEquals(
      "  <k1>v1</k1>" + nl,
      xml.toString()
    );
  }

  //-> kv
  @Test public void kv_array() {
    xml.kv("  ",
      new String[] {"k1>v1", "k2>v2"}
    );
    assertEquals(
      "  <k1>v1</k1>" + nl +
      "  <k2>v2</k2>" + nl,
      xml.toString()
    );
  }
  @Test public void kv() {
    xml.kv("  ", "k>v");
    assertEquals(
      "  <k>v</k>" + nl, xml.toString()
    );
  }
  @Test public void kv_no_v() {
    xml.kv("  ", "k");
    assertEquals(
      "", xml.toString()
    );
  }

  //-> xml
  @Test public void xml_single_line() {
    xml.xml("  ",
      "  <k1>v1</k1>" + nl
    );
    assertEquals(
      "    <k1>v1</k1>" + nl,
      xml.toString()
    );
  }
  @Test public void xml_multi_line() {
    xml.xml("  ",
      "  <k1>v1</k1>" + nl +
      "  <k2>" + nl +
      "    <k3>v3<k3>" + nl +
      "  </k2>" + nl
    );
    assertEquals(
      "    <k1>v1</k1>" + nl +
      "    <k2>" + nl +
      "      <k3>v3<k3>" + nl +
      "    </k2>" + nl,
      xml.toString()
    );
  }
}
