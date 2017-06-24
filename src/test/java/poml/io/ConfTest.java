package poml.io;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import poml.UtCase;

public class ConfTest extends UtCase {

  //-> has
  @Test public void has() {
    Conf c = new Conf();
    c.kv.put("key", "val");
    Assert.assertTrue(c.has("key"));
    Assert.assertFalse(c.has("k"));
  }

  //-> val
  /// ok
  @Test public void ok_val() {
    poml("key=val");
    Assert.assertEquals("val", poml.conf.val("key"));
  }
  @Test public void ok_val_ltrim() {
    poml("key=  val");
    Assert.assertEquals("val", poml.conf.val("key"));
  }
  /// ng
  @Test public void ng_val_null() {
    poml("");
    try {
      poml.conf.val("key");
      fail();
    } catch (RuntimeException e) {
      err("Invalid config [key=key] [val=null]", e);
    }
  }
  @Test public void ng_val_empty() {
    poml("key=");
    try {
      poml.conf.val("key");
      fail();
    } catch (RuntimeException e) {
      err("Invalid config [key=key] [val=]", e);
    }
  }

  //-> csv
  /// ok
  @Test public void ok_csv() {
    poml("key=v1, v2");
    String[] val = poml.conf.csv("key");
    Assert.assertEquals(2, val.length);
    Assert.assertEquals("v1", val[0]);
    Assert.assertEquals("v2", val[1]);
  }
  @Test public void ok_csv_single() {
    poml("key=v1");
    String[] val = poml.conf.csv("key");
    Assert.assertEquals(1, val.length);
    Assert.assertEquals("v1", val[0]);
  }
  @Test public void ok_csv_ltrim() {
    poml("key=  v1,  v2");
    String[] val = poml.conf.csv("key");
    Assert.assertEquals(2, val.length);
    Assert.assertEquals("v1", val[0]);
    Assert.assertEquals("v2", val[1]);
  }
  @Test public void ok_csv_replace() {
    poml("key=v\\,1, v\\,2");
    String[] val = poml.conf.csv("key");
    Assert.assertEquals(2, val.length);
    Assert.assertEquals("v,1", val[0]);
    Assert.assertEquals("v,2", val[1]);
  }
  @Test public void ng_csv_none() {
    poml("key= , v");
    try {
      poml.conf.csv("key");
      fail();
    } catch (RuntimeException e) {
      err("Invalid config [key=key] [val= , v]", e);
    }
  }


  //-> xml
  /// ok
  @Test public void ok_xml() {
    poml(
      "key={" + nl +
      "  <k>v</k>" + nl +
      "}" + nl
    );
    Assert.assertEquals(
      "  <k>v</k>"+nl, poml.conf.xml("key")
    );
  }
  /// ng
  @Test public void ng_xml_null() {
    poml("");
    try {
      poml.conf.xml("k");
      fail();
    } catch (RuntimeException e) {
      err("Invalid config [key=k] [val=null]", e);
    }
  }
  @Test public void ng_xml_empty() {
    poml(
      "k={" + nl +
      "}" + nl
    );
    try {
      poml.conf.xml("k");
      fail();
    } catch (RuntimeException e) {
      err("Invalid config [key=k] [val=]", e);
    }
  }
}
