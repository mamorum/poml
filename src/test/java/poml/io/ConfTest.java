package poml.io;

import static org.junit.Assert.fail;

import org.junit.Test;

import poml.UtCase;

public class ConfTest extends UtCase {

  //-> NG
  @Test public void ng_val_none() {
    poml("");
    try {
      poml.conf.val("k");
      fail();
    } catch (IllegalStateException e) {
      err("Invalid config [key=k] [val=null]", e);
    }
  }
  @Test public void ng_val_blank() {
    poml("k=");
    try {
      poml.conf.val("k");
      fail();
    } catch (IllegalStateException e) {
      err("Invalid config [key=k] [val=]", e);
    }
  }
  @Test public void ng_csv_none() {
    poml("k= , ");
    try {
      poml.conf.csv("k");
      fail();
    } catch (IllegalStateException e) {
      err("Invalid config [key=k] [val= , ]", e);
    }
  }
}
