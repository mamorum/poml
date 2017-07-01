package poml.io;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import poml.UtCase;

public class PomlTest extends UtCase {

  //-> parseConfig
  @Test public void empty() {
    poml("");
    Assert.assertEquals(0, poml.conf.kv.size());
  }
  @Test public void comment() {
    poml("# comment");
    Assert.assertEquals(0, poml.conf.kv.size());
  }
  @Test public void ng_noKey1() {
    try {
      poml("aaa");
      fail();
    } catch (RuntimeException e) {
      err("Invalid config [line=aaa]", e);
    }
  }
  @Test public void ng_noKey2() {
    try {
      poml("=aa");
      fail();
    } catch (RuntimeException e) {
      err("Invalid config [line==aa]", e);
    }
  }
  @Test public void eqEnd1() {
    poml(
      "key=" + nl +
      "  val"
    );
    Assert.assertEquals(
      "  val", poml.conf.kv.get("key")
    );
  }
  @Test public void eqEnd2() {
    poml(
      "key=" + nl +
      "  val="
    );
    Assert.assertEquals(
      "  val=", poml.conf.kv.get("key")
    );
  }
  @Test public void eqEnd3() {
    poml(
      "key=" + nl +
      "  a, b, c," + nl +
      "  d, e, f\\,"  + nl
    );
    Assert.assertEquals(
      "  a, b, c,  d, e, f\\,", poml.conf.kv.get("key")
    );
  }
  @Test public void csvEnd1() {
    poml(
      "key=a," + nl +
      "  b," + nl +
      "  c"
    );
    Assert.assertEquals(
      "a,  b,  c", poml.conf.kv.get("key")
    );
  }
  @Test public void csvEnd2() {
    poml(
      "key=a," + nl +
      "  b\\,c"
    );
    Assert.assertEquals(
      "a,  b\\,c", poml.conf.kv.get("key")
    );
  }
  @Test public void csvEnd3() {
    poml(
      "key=a," + nl +
      "  b\\," + nl
    );
    Assert.assertEquals(
      "a,  b\\,", poml.conf.kv.get("key")
    );
  }
  @Test public void csvEnd4() {
    poml(
      "key=a," + nl +
      "  b=" + nl
    );
    Assert.assertEquals(
      "a,  b=", poml.conf.kv.get("key")
    );
  }
  @Test public void xmlEnd1() {
    poml(
      "key={" + nl +
      "  <k>v</k>" + nl +
      "}" + nl
    );
    Assert.assertEquals(
      "  <k>v</k>" + nl,
      poml.conf.kv.get("key")
    );
  }
  @Test public void xmlEnd2() {
    poml(
      "key={" + nl +
      "  <k>v</k>" + nl +
      "  <k />" + nl +
      "}" + nl
    );
    Assert.assertEquals(
      "  <k>v</k>" + nl + "  <k />" + nl,
      poml.conf.kv.get("key")
    );
  }
  @Test public void keyValEnd1() {
    poml("key=val");
    Assert.assertEquals(
      "val", poml.conf.kv.get("key")
    );
  }
  @Test public void keyValEnd2() {
    poml("key=val=");
    Assert.assertEquals(
      "val=", poml.conf.kv.get("key")
    );
  }
  @Test public void keyValEnd3() {
    poml("key==a\\,");
    Assert.assertEquals(
      "=a\\,", poml.conf.kv.get("key")
    );
  }
  @Test public void keyValEnd4() {
    poml("key=a={");
    Assert.assertEquals(
      "a={", poml.conf.kv.get("key")
    );
  }
}
