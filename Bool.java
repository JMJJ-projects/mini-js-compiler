public class Bool extends ExpressionA {
  public String bool;
    
  public Bool(String bool) {
    this.bool = bool;
  }
  
  public String toString() {
    return this.bool;
  }
  
  public String toAssembly() { return "CstBo " + bool;}

}
