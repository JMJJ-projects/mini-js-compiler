public class SetVar extends ExpressionA {
  public String id;
  public ExpressionA arg;
    
  public SetVar(String id, ExpressionA arg) {
    this.id = id;
    this.arg = arg;
    this.taille = arg.getTaille() + 1; 
  }
  
  public String toString() {
    return arg.toString() + "\nVar " + this.id;
  }
  
   public String toAssembly() { return this.arg.toAssembly() + "\nSetVar " + this.id;}

}
