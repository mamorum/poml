package poml;

import java.io.File;

public class Opt {
  private static final String cdir = " ./";
  private static final String nl = System.lineSeparator();
  public void help() {
    System.out.print((new StringBuilder(
      "Convert pom.poml to pom.xml")).append(nl).append(nl
      ).append("Usage: poml [option]").append(nl).append(nl
      ).append("Option:").append(nl
      ).append("  -h, help   \t   print this help").append(nl
      ).append("  -v, version\t   print poml version").append(nl
//   ).append("  init       \t   create pom.poml, pom.xml and src dirs").append(nl
      ).append("  mkdirs     \t   create src dirs for maven project").append(nl
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
    System.out.print((new StringBuilder(
      "[POML:INFO] Created dirs")).append(nl
      ).append(cdir).append(mj).append(nl
      ).append(cdir).append(mr).append(nl
      ).append(cdir).append(tj).append(nl
      ).append(cdir).append(tr).append(nl
    ).toString());
  }
  public void init() {
    // prompt
    // create pom.poml
    // convert pom.poml to pom.xml
    // mkdirs();
  }
}