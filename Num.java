public class Num extends ExpressionA {
  public double num;
    
  public Num(double num) {
    this.num = num;
  }
  
  public String toString() {
  //Pour que 12.0 -> 12 dans le parseur
  if(num == (int)num) {
      return Integer.toString((int)num);
  }
    return Double.toString(num);
  }

}

