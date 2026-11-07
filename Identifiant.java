public class Identifiant extends ExpressionA {
  public String id;
    
  public Identifiant(String id) {
    this.id = id;
    this.taille = 1; // GetVar
  }
  
  public ExpressionA simplifie() {
      return this;
    }
  
  public String toString() {
    return this.id;
  }
  
   public String toAssembly() { return "GetVar " + this.id;}

}
