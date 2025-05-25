public class Identifiant extends ExpressionA {
  public String id;
    
  public Identifiant(String id) {
    this.id = id;
  }
  
  public String toString() {
    return this.id;
  }
  
   public String toAssembly() { return "GetVar " + this.id;}

}
