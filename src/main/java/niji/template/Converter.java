package niji.template;

@FunctionalInterface
public interface Converter {
  void toXml(Src src, StringBuilder xml);
}
