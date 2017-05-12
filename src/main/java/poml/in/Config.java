package poml.in;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import poml.Throw;

public class Config {
  boolean hasLayout = false;
  private Map<String, String> p = new HashMap<>();
  private Map<String, List<String>> tags = new HashMap<>();

  // -> For checking config key.
  public boolean has(String key) {
    return p.containsKey(key);
  }
  public boolean hasTag(String key) {
    return tags.containsKey(key);
  }

  // -> For getting config values.
  // key=val
  //  - throw: if val is null or blank
  //  - return: not null or blank
  public String val(String key) {
    String val = p.get(key);
    if (none(val)) Throw.noConf(key);
    return ltrim(val);
  }
  // key=val, val, ...
  //  - throw: if array element is null or blank
  //  - return: array (size 1+, element length 1+)
  public String[] vals(String key) {
    return split(key, val(key));
  }
  //// split val with "," -> replace "\\," to ","
  private static final String esc = "\\,";
  private String[] split(String key, String val) {
    boolean hasEsc = val.contains(esc);
    String delim = hasEsc ? "(?<!\\\\)," : ",";
    String[] vals = val.split(delim);
    for (int i=0; i<vals.length; i++) {
      if (none(vals[i])) Throw.badConf(key, val);
      if(hasEsc) vals[i] = vals[i].replace(esc, ",");
      vals[i] = ltrim(vals[i]);
    }
    return vals;
  }
  // key=k>v, k>v, ...
  //  - throw: if map element is null or blank
  //  - return: map (size 1+)
  public Map<String, String> map(String key) {
    String val = val(key);
    Map<String, String> map = new LinkedHashMap<>();
    for (String kv: split(key, val)) {
      if (!put(kv, map)) Throw.badConf(key, val);
    }
    return map;
  }
  //// "k>v" -> put("k", "v")
  ////" k > v " -> put("k", "v ")
  private static boolean put(
    String kv, Map<String, String> map
  ) {
    int pos = kv.indexOf('>');
    if (pos == -1) return false;
    String k = kv.substring(0, pos);
    String v = kv.substring(pos + 1);
    if (none(k)) return false;
    if (none(v)) return false;
    map.put(k.trim(), ltrim(v));
    return true;
  }
  // -> for getting config tags from "{ <k>v</k>  ... }"
  public String tag(String key, String space) {
    List<String> lines = tags.get(key);
    if (lines == null) Throw.noConf(key);
    // TODO check if lines exist ?
    StringBuilder sb = new StringBuilder();
    for (String line: lines) {
      sb.append(space).append(line)
        .append(System.lineSeparator());
    }
    return sb.toString();
  }
  private static boolean none(String s) {
    if (s == null || "".equals(s)) return true;
    return false;
  }
  private static String ltrim(String s) {
    int len=s.length(), i=0;
    char[] c = s.toCharArray();
    while ((i < len) && (c[i] <= ' ')) i++;
    return (i > 0 ? s.substring(i) : s);
  }

  // -> for parsing config section of pom.poml.
  public void parse(BufferedReader lines) {
    this.lines = lines;
    try { parse(); }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  private BufferedReader lines;
  private void parse() throws IOException {
    String line;
    while ((line = lines.readLine()) != null) {
      if (line.length() == 0) continue;  // none
      if (line.charAt(0) == '#') continue;  // comment
      if (line.equals("---")) {  // layout
        hasLayout = true; break;
      }
      char last = line.charAt(line.length()-1);
      if (last == '{') addTags(line);
      else if (isContinuing(last)) addLines(line);
      else addLine(line);
    }
  }

  private void addTags(String firstLine) throws IOException {
    // first line
    int pos = firstLine.indexOf('=');
    if (pos == -1) return;
    String key = firstLine.substring(0, pos).trim();
    // second+ lines
    ArrayList<String> val = new ArrayList<>();
    String line; char last;
    while ((line = lines.readLine()) != null) {
      last = line.charAt(line.length()-1);
      if (last == '}') break;  // end
      else val.add(line);
    }
    tags.put(key, val);
  }

  private boolean isContinuing(char last) {
    if (last == '=') return true;
    if (last == ',') return true;
    return false;
  }
  private void addLines(String firstLine) throws IOException {
    StringBuilder sb = new StringBuilder(firstLine);
    String line; char last;
    while ((line = lines.readLine()) != null) {
      sb.append(line);
      last = line.charAt(line.length()-1);
      if (isContinuing(last)) continue;
      else break;
    }
    addLine(sb.toString());
  }
  private void addLine(String line) {
    int pos = line.indexOf('=');
    if (pos == -1) return;
    String k = line.substring(0, pos).trim();
    String v = line.substring(pos + 1);
    p.put(k, v);
  }
}
