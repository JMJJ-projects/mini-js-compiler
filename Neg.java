public class Neg extends ExpressionA {

public ExpressionA arg;

public Neg(ExpressionA arg) {
  this.arg = arg;
}

public ExpressionA simplifie() {
      ExpressionA a = arg.simplifie();
      if(a instanceof Num)
      {
        Num a_num = (Num) a;
      
        if(a_num.nan == "NaN")
        {
          return new Num("NaN");
        }
        double val = (((Num) a).num);
        return new Num(-val);
      }
      return new Neg(a);
    }

public String toString() {
  return String.format("neg(%1$s)", arg.toString());
}

@Override
public String toAssembly() { return arg.toAssembly() + "\nNegaNb";}

}
