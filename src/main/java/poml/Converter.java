package poml;

public interface Converter {

  String name();
  void convert(Src src, Dst dst);
  
  public static String nl = System.lineSeparator();
  public static String
    sp2="  ",
    sp4=sp2+sp2,
    sp6=sp2+sp4,
    sp8=sp2+sp6;
}

