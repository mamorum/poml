package poml;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import poml.convert.Basic;

public class Opt {
  private static final PrintStream out = System.out;
  private static final String nl = System.lineSeparator();
  public void help() {
    out.println("Convert pom.poml to pom.xml");
    out.println();
    out.println("Usage: poml [option]");
    out.println();
    out.println("Option:");
    out.println("  -h, help   \t   print this help");
    out.println("  -v, version\t   print poml version");
    out.println("  mkdirs     \t   create src dirs for maven project");
    out.println("  init       \t   create pom.poml, pom.xml and src dirs");
  }
  public void version() {
    out.println(
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
    out.println("[POML:INFO] Created dirs");
    out.println(smj);
    out.println(smr);
    out.println(stj);
    out.println(str);
  }
  // init ->
  private BufferedReader in;
  public void init() throws Throwable {
    in = new BufferedReader(new InputStreamReader(System.in));
    out.println("This option creates pom.poml and maven project.");
    out.println("Please answer some questions. (Press ^C to quit.)");
    out.println();
    String poml = askPoml();
    out.println();
    out.println("content of pom.poml: ");
    out.println();
    out.println(poml);
    out.println();
    String ok = ask("ok?", "yes");
    if ("yes".equals(ok)) createPrj(poml);
    else out.println("Quit.");
  }
  private String askPoml() throws IOException {
    String dir = (
      new File(".")
    ).getAbsoluteFile().getParentFile().getName();
    return (new StringBuilder(
      ).append(Basic.pkg).append("="
      ).append(ask("groupId", "com.domain")).append(":"
      ).append(ask("artifactId", dir)).append(":"
      ).append(ask("version", "1.0.0")).append(":"
      ).append(ask("packaging", "jar")).append(nl
      ).append(Basic.props).append("="
      ).append(Basic.enc).append(">"
      ).append(ask("encoding", "UTF-8")).append(", "
      ).append(Basic.jdk).append(">"
      ).append(ask("jdk version", "1.8"))
    ).toString();
  }
  private String ask(String item, String defVal) throws IOException {
    String prompt = (new StringBuilder(
      ).append(item).append(": (").append(defVal).append(") ")
    ).toString();
    out.print(prompt);
    String usrVal = in.readLine();
    return "".equals(usrVal) ? defVal : usrVal;
  }
  private void createPrj(String poml) throws Throwable {
    Main.save(poml, "pom.poml");
    out.println();
    out.println("[POML:INFO] Created pom.poml");
    Main.main("pom.poml", "pom.xml"); // convert poml to xml
    mkdirs();
  }
}