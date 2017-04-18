package poml.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import poml.tool.Throw;

public class Config {
  boolean hasLayout = false;
  private Map<String, String> p = new HashMap<>();
  private Map<String, List<String>> tags = new HashMap<>();
  
  public void parse(BufferedReader in) {
    try { (new Parser(in, this)).parse(); }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  // -> For checking key.
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
    //String pv = p.getProperty(key);
    String pv = p.get(key);
    if (pv == null) Throw.noConf(key);
    String val = pv.trim();
    if ("".equals(val)) Throw.badConf(key, pv);
    return val;
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
      vals[i] = vals[i].trim();
      if(hasEsc) vals[i] = vals[i].replace(esc, ",");
      if (none(vals[i])) Throw.badConf(key, val);
    }
    return vals;
  }
  // key=k:v, k:v, ...
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
  //// "k:v" -> put("k", "v")
  private static boolean put(
    String kv, Map<String, String> map
  ) {
    int pos = kv.indexOf(':');
    if (pos == -1) return false;
    String k = kv.substring(0, pos).trim();
    String v = kv.substring(pos + 1).trim();
    if (none(k)) return false;
    if (none(v)) return false;
    map.put(k, v);
    return true;
  }
  private static boolean none(String s) {
    if (s == null || "".equals(s)) return true;
    return false;
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
  
  // NOT thread safe
  static class Parser {
    Parser(BufferedReader in, Config conf) {
      this.in = in;
      this.conf = conf;
    }
    private BufferedReader in;
    private Config conf;
    
    private String line;
    private int length;
    private char start, last;
    private void length() { length = line.length(); }
    private void start() { start = line.charAt(0); }
    private void last() { last = line.charAt(length - 1); }
    
    void parse() throws IOException {
      while ((line = in.readLine()) != null) {
        length();
        if (length == 0) continue;
        start();
        if (isComment()) continue;
        last();
        if (isLayout()) break;
        else if (isContinuing()) addLines();
        else if (isStartBracket()) addTags();
        else addLine();
      }
    }

    private boolean isComment() {
      return start == '#';
    }
    private boolean isStartBracket() {
      if (last == '{') return true;
      return false;
    }
    private boolean isContinuing() {
      if (last == '=') return true;
      if (last == ',') return true;
      return false;
    }
    private boolean isLayout() {
      if (length == 3 && start == '-' && last == '-') {
        if (line.charAt(1) == '-') conf.hasLayout = true;
      }
      return conf.hasLayout;
    }

    private void addTags() throws IOException {
      // first line
      int pos = line.indexOf('=');
      if (pos == -1) return;
      String key = line.substring(0, pos).trim();
      // second+ lines
      ArrayList<String> val = new ArrayList<>();
      while ((line = in.readLine()) != null) {
        length(); start(); last();
        if (!isTagEnd()) break;
        val.add(line);
      }
      conf.tags.put(key, val);
    }
    private boolean isTagEnd() {
      if (last == '>') return true;
      return false;
    }

    private void addLines() throws IOException {
      StringBuilder sb = new StringBuilder(line);
      while ((line = in.readLine()) != null) {
        sb.append(line);
        length(); start(); last();
        if (!isContinuing()) break;
      }
      line = sb.toString();
      addLine();
    }
    private void addLine() {
      int pos = line.indexOf('=');
      if (pos == -1) return;
      String k = line.substring(0, pos).trim();
      String v = line.substring(pos + 1).trim();
      conf.p.put(k, v);
    }
  }
}
