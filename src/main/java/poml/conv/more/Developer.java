package poml.conv.more;

import java.util.Map;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;

public class Developer implements Converter{
  @Override public String name() { return "developer"; }

  @Override public void convert(Poml in, Xml out) {
    String[] devs = in.conf.vals(name());
    for (String $dev: devs) {
      Map<String, String> kv = in.conf.map($dev);
      out.line("    <developer>");
      out.tag(sp6, id, kv.get(id));
      out.tag(sp6, name, kv.get(name));
      out.tag(sp6, mail, kv.get(mail));
      out.tag(sp6, url, kv.get(url));
      out.line("    </developer>");
    }
  }
  private static final String
    id="id", name="name", mail="email", url="url";
}