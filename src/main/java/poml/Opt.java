package poml;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

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
      ).append("  init       \t   create pom.poml, pom.xml and src dirs").append(nl
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
  private BufferedReader in;
  private String in(String item, String defVal) throws IOException {
    System.out.print(item);
    System.out.print(": (");
    System.out.print(defVal);
    System.out.print(") ");
    String usrVal = in.readLine();
    return "".equals(usrVal) ? defVal : usrVal;
  }
  public void init() throws Throwable {
    // prompt
    System.out.println("This option creates pom.poml and maven project.");
    System.out.println("Please answer some questions. (Press ^C to quit.)");
    System.out.println();
    in = new BufferedReader(new InputStreamReader(System.in));
    File cdir = new File(".").getAbsoluteFile().getParentFile();
    String grp = in("groupId", "org.sample");
    String art = in("artifactId", cdir.getName());
    String ver = in("version", "1.0.0");
    String pkg = in("packaging", "jar");
    String enc = in("encoding", "UTF-8");
    String jdk = in("jdk version", "1.8");
    // no junit.

    System.out.println();
    System.out.println("content of pom.poml: ");
    System.out.println();
    String poml = (new StringBuilder(
      ).append("pkg="
      ).append(grp).append(":").append(art).append(":"
      ).append(ver).append(":").append(pkg).append(nl
      ).append("properties="
      ).append("&encoding>").append(enc).append(", "
      ).append("&compiler>").append(jdk)).toString();
    System.out.println(poml);
    System.out.println();
    String ok = in("ok?", "yes");
    if (!"yes".equals(ok)) {
      System.out.println("Quit.");
      return;
    }
    // create pom.poml
    Main.save(poml, "pom.poml");
    System.out.println();
    System.out.println("[POML:INFO] Created pom.poml");
    // convert pom.poml to pom.xml
    Main.main("pom.poml", "pom.xml");
    mkdirs();
  }
}