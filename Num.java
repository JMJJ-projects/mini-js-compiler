public class Num extends ExpressionA {

    public double num;
    public String nan;
    
    public Num(double num) {
        this.num = num;
        this.taille = 1;  // "CstNb X" is 1 instruction
    }
    
    public Num(String nan) {
      this.nan = nan;
      this.taille = 1;
    }
    
    public ExpressionA simplifie() {
      return this;
    }
  
  public String toString() {
    if(this.nan == "NaN")
    {
      return this.nan;
    }
    //Pour que 12.0 -> 12 dans le parseur
    if(num == (int)num) {
      return Integer.toString((int)num);
    }
      return Double.toString(num);
    }

  @Override
  public String toAssembly() { 
    if(this.nan == "NaN")
    {
      return "CstNb " + this.nan;
    }
    if(num == (int)num) {
      return "CstNb " + Integer.toString((int)num);
    }
      return "CstNb " + Double.toString(num);
    }

} 

