package poml;

public interface Converter {
  String key();
  void convert(Src src, Dst dst);
}

