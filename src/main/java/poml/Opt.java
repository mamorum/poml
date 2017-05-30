package poml;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import poml.convert.Basic;

public class Opt {
  private static final PrintStream o = System.out;
  public void help() {
    o.println("Convert pom.poml to pom.xml");
    o.println();
    o.println("Usage: poml [option]");
    o.println();
    o.println("Option:");
    o.println("  -h, help   \t   print this help");
    o.println("  -v, version\t   print poml version");
    o.println("  mkdirs     \t   create src dirs for maven project");
    o.println("  init       \t   create pom.poml, pom.xml and src dirs");
  }
  public void version() {
    o.println(
      Main.class.getPackage()
        .getImplementationVersion()
    );
  }
  // mkdirs ->
  private static final String
    mj="src/main/java", smj=" "+mj,
    mr="src/main/resources", smr=" "+mr,
    tj="src/test/java", stj=" "+tj,
    tr="src/test/resources", str=" "+tr;
  public void mkdirs() {
    (new File(mj)).mkdirs();
    (new File(mr)).mkdirs();
    (new File(tj)).mkdirs();
    (new File(tr)).mkdirs();
    o.println("[INFO] Created dirs");
    o.println(smj);
    o.println(smr);
    o.println(stj);
    o.println(str);
  }
  // init ->
  public void init() throws Throwable {
    o.println("This option creates pom.poml and maven project.");
    o.println("Please answer some questions. (Press ^C to quit.)");
    o.println();
    String poml = askPoml();
    o.println();
    o.println("content of pom.poml: ");
    o.println();
    o.println(poml);
    o.println();
    String ok = ask("ok?", "yes");
    if ("yes".equals(ok)) createPrj(poml);
    else o.println("Quit.");
  }
  private String askPoml() throws IOException {
    String cdir = (
      new File(".")  // current dir path
    ).getAbsoluteFile().getParentFile().getName();
    in = new BufferedReader(new InputStreamReader(System.in));
    return (new StringBuilder(
      ).append(Basic.pkg).append("="
      ).append(ask("groupId", "com.domain")).append(":"
      ).append(ask("artifactId", cdir)).append(":"
      ).append(ask("version", "1.0.0")).append(":"
      ).append(ask("packaging", "jar")).append(System.lineSeparator()
      ).append(Basic.props).append("="
      ).append(Basic.enc).append(">"
      ).append(ask("encoding", "UTF-8")).append(", "
      ).append(Basic.jdk).append(">"
      ).append(ask("jdk version", "1.8"))
    ).toString();
  }
  private BufferedReader in;
  private String ask(String item, String defVal) throws IOException {
    o.print((new StringBuilder(  // question
      ).append(item).append(": (").append(defVal).append(") ")
    ).toString());
    String usrVal = in.readLine();
    return "".equals(usrVal) ? defVal : usrVal;
  }
  private void createPrj(String poml) throws Throwable {
    Main.save(poml, "pom.poml");
    o.println();
    o.println("[INFO] Created pom.poml");
    Main.convert("pom.poml", "pom.xml");
    mkdirs();
  }
}