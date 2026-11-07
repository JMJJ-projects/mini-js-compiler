/** print(e);  — evaluates e and prints it (JSM instruction Print). */
public class Print extends ExpressionA {
  public ExpressionA arg;

  public Print(ExpressionA arg) {
    this.arg = arg;
    this.taille = arg.getTaille() + 1;
  }

  public ExpressionA simplifie() {
    return new Print(arg.simplifie());
  }

  public String toString() {
    return "print(" + arg + ")";
  }

  public String toAssembly() {
    return arg.toAssembly() + "\nPrint";
  }
}
