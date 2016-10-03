package poml.converter.more;

import java.util.Map;

import poml.Converter;
import poml.Dst;
import poml.Src;

// More Project Infomation
//   -> name, desc, url, inceptYear
public class Info implements Converter {
    @Override public String name() { return "info"; }
    @Override public void convert(Src src, Dst dst) {
      Map<String, String> kv = src.propMap(name());
      if (kv.size() == 0) return;
      dst.printKvTags(sp2, kv);
    }
}
