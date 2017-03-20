package poml.conv.basic;

import java.util.Map;

import poml.conv.Converter;
import poml.in.Poml;
import poml.out.Xml;

public class Property implements Converter {

  @Override public String name() { return "property"; }
  
  @Override public void convert(Poml poml, Xml xml) {
    Map<String, String> kv = poml.conf.map(name(), false);
    replaceKey(kv, encoding, encodings);
    replaceKey(kv, compiler, compilers);
    xml.println("  <properties>");
    xml.printKvTags(sp4, kv);
    xml.println("  </properties>");
  }
  
  private static final String encoding = "$encoding";
  private static final String[] encodings = {
    "project.build.sourceEncoding", "project.reporting.outputEncoding"
  };
  private static final String compiler = "$compiler";
  private static final String[] compilers = {
    "maven.compiler.source", "maven.compiler.target"
  };  
  private void replaceKey(Map<String, String> kv, String from, String[] to) {
    if (!kv.containsKey(from)) return;
    String v = kv.remove(from);
    for (String k: to) kv.put(k, v);
  }
}
