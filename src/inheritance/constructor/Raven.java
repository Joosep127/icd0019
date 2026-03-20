package inheritance.constructor;

public class Raven extends Bird {
  public Raven() {
      super("None");
      System.out.println("Constructing Raven");
  }

  public Raven(String name) {
    super("None");
    System.out.println("constructing Car" + name);
  }
}
