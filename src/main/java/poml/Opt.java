package poml;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import poml.convert.Basic;

public class Opt {
  private static final PrintStream o = System.out;
  static void help() {
    o.println("Usage: poml [option]");
    o.println();
    o.println("  Convert pom.poml to pom.xml (if no option is specified)");
    o.println();
    o.println("Option:");
    o.println("  -h, help   \t   print this help");
    o.println("  -v, version\t   print poml version");
    o.println("  mkdirs     \t   create src dirs for maven project");
    o.println("  init       \t   create pom.poml, pom.xml and src dirs");
  }
  static void version() {
    o.println(
      Opt.class.getPackage().getImplementationVersion()
    );
  }
  // mkdirs ->
  private static final String
    mj="src/main/java", mr="src/main/resources",
    tj="src/test/java", tr="src/test/resources";
  static void mkdirs() {
    (new File(mj)).mkdirs();
    (new File(mr)).mkdirs();
    (new File(tj)).mkdirs();
    (new File(tr)).mkdirs();
    o.println("[INFO] Created dirs");
    o.print(" "); o.println(mj);
    o.print(" "); o.println(mr);
    o.print(" "); o.println(tj);
    o.print(" "); o.println(tr);
  }
  // init ->
  static void init() throws Throwable {
    o.println("This option creates pom.poml and maven project.");
    o.println("Please answer some questions. (Press ^C to quit.)");
    o.println();
    in = new BufferedReader(new InputStreamReader(System.in));
    String grp = ask("groupId", "com.domain");
    String art = ask("artifactId", name(new File("."))); // current dir
    String ver = ask("version", "1.0.0");
    String pkg = ask("packaging", "jar");
    String enc = ask("encoding", "UTF-8");
    String jvc = ask("javac version", "1.8");
    o.println();
    String poml = (new StringBuilder(
      ).append(Basic.pkg).append("="
      ).append(grp).append(":").append(art).append(":"
      ).append(ver).append(":").append(pkg
      ).append(System.lineSeparator()
      ).append(Basic.properties).append("="
      ).append(Basic.enc)).append(enc).append(", "
      ).append(Basic.javac).append(jvc
    ).toString();
    o.println("content of pom.poml: ");
    o.println();
    o.println(poml);
    o.println();
    String ok = ask("ok?", "yes");
    if ("yes".equals(ok)) project(poml);
    else o.println("Quit.");
  }
  private static BufferedReader in;
  private static String ask(String item, String defVal) throws IOException {
    o.print((new StringBuilder(  // Question -> "item: (defval) "
      ).append(item).append(": (").append(defVal).append(") ")
    ).toString());
    String usrVal = in.readLine();
    return "".equals(usrVal) ? defVal : usrVal;
  }
  private static String name(File f) {
    return f.getAbsoluteFile().getParentFile().getName();
  }
  private static void project(String poml) throws Throwable {
    Main.save(poml, "pom.poml");
    o.println();
    o.println("[INFO] Created pom.poml");
    Main.convert("pom.poml", "pom.xml");
    mkdirs();
  }
}