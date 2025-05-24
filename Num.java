public class Num extends ExpressionA {

  public double num;
  public String nan;
    
  public Num(double num) {
    this.num = num;
  }
  public Num(String nan) {
    this.nan = nan;
  }
  
  public String toString() {
  if(nan=="NaN")
  {
    return nan;
  }
  //Pour que 12.0 -> 12 dans le parseur
  if(num == (int)num) {
      return Integer.toString((int)num);
    }
      return Double.toString(num);
    }

  @Override
  public String toAssembly() { 
    if(nan=="NaN")
    {
    return "CstNb " + nan;
    }
    if(num == (int)num) {
      return "CstNb " + Integer.toString((int)num);
    }
      return "CstNb " + Double.toString(num);
    }

}
