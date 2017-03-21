package poml.cmd;

import java.io.File;

import poml.Main;

public class Opt {
  public static void help() {
    Msg.init(
      ).add("Convert pom.poml to pom.xml").nl().nl(
      ).add("Usage: poml [option]").nl().nl(
      ).add("Option:").nl(
      ).add("  -h, help   \t   print this help").nl(
      ).add("  -v, version\t   print poml version").nl(
      // ).add("  init       \t   create pom.poml, pom.xml and src dirs").nl(
      ).add("  mkdirs     \t   create src dirs for maven project").nl(
    ).out();
  }
  public static void version() {
    System.out.println(
      Main.class.getPackage()
        .getImplementationVersion()
    );
  }
  public static void mkdirs() {
    String mj = "src/main/java";
    String mr = "src/main/resources";
    String tj = "src/test/java";
    String tr = "src/test/resources";
    (new File(mj)).mkdirs();
    (new File(mr)).mkdirs();
    (new File(tj)).mkdirs();
    (new File(tr)).mkdirs();
    String cdir = " ./";
    Msg.init(
      ).add("[POML:INFO] Created dirs").nl(
      ).add(cdir).add(mj).nl(
      ).add(cdir).add(mr).nl(
      ).add(cdir).add(tj).nl(
      ).add(cdir).add(tr).nl(
    ).out();
  }
  public static void init() {
    // prompt
    // create pom.poml
    // convert pom.poml to pom.xml
    // mkdirs();
  }
}