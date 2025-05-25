public class Num extends ExpressionA {

    public double num;
    
    public Num(double num) {
        this.num = num;
        this.taille = 1;  // "CstNb X" is 1 instruction
    }
  
  public String toString() {
    //Pour que 12.0 -> 12 dans le parseur
    if(num == (int)num) {
      return Integer.toString((int)num);
    }
      return Double.toString(num);
    }

  @Override
  public String toAssembly() { 
    if(num == (int)num) {
      return "CstNb " + Integer.toString((int)num);
    }
      return "CstNb " + Double.toString(num);
    }

} 

