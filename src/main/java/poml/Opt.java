package poml;

import java.io.File;

import poml.io.Txt;

public class Opt {
  public void help() {
    System.out.print(Txt.init(
      ).add("Convert pom.poml to pom.xml").nl().nl(
      ).add("Usage: poml [option]").nl().nl(
      ).add("Option:").nl(
      ).add("  -h, help   \t   print this help").nl(
      ).add("  -v, version\t   print poml version").nl(
      // ).add("  init       \t   create pom.poml, pom.xml and src dirs").nl(
      ).add("  mkdirs     \t   create src dirs for maven project").nl(
    ).toString());
  }
  public void version() {
    System.out.println(
      Main.class.getPackage()
        .getImplementationVersion()
    );
  }
  public void mkdirs() {
    String mj = "src/main/java";
    String mr = "src/main/resources";
    String tj = "src/test/java";
    String tr = "src/test/resources";
    (new File(mj)).mkdirs();
    (new File(mr)).mkdirs();
    (new File(tj)).mkdirs();
    (new File(tr)).mkdirs();
    String cdir = " ./";
    System.out.print(Txt.init(
      ).add("[POML:INFO] Created dirs").nl(
      ).add(cdir).add(mj).nl(
      ).add(cdir).add(mr).nl(
      ).add(cdir).add(tj).nl(
      ).add(cdir).add(tr).nl(
    ).toString());
  }
  public void init() {
    // prompt
    // create pom.poml
    // convert pom.poml to pom.xml
    // mkdirs();
  }
}