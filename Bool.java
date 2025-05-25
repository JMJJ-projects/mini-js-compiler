public class Bool extends ExpressionA {
  public String bool;
    
  public Bool(String bool) {
    this.bool = bool;
    this.taille = 1;
  }
  
  public String toString() {
    return this.bool;
  }
  
  public String toAssembly() { return "CstBo " + bool;}

}
