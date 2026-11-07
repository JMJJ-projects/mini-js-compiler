/**
 * Expression statement "e;": evaluates e for its side effects and drops the result,
 * so loops do not leave values piling up on the stack.
 */
public class Discard extends ExpressionA {
  public ExpressionA arg;

  public Discard(ExpressionA arg) {
    this.arg = arg;
    this.taille = arg.getTaille() + 1;
  }

  public ExpressionA simplifie() {
    return new Discard(arg.simplifie());
  }

  public String toString() {
    return arg.toString() + ";";
  }

  public String toAssembly() {
    return arg.toAssembly() + "\nDrop";
  }
}
