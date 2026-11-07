public class Not extends ExpressionA {

public ExpressionA arg;

public Not(ExpressionA arg) {
  this.arg = arg;
  this.taille = arg.getTaille() + 1; // arg + Not
}

public ExpressionA simplifie() {
      ExpressionA a = arg.simplifie();
      if(a instanceof Bool)
      {
        Bool a_bool = (Bool) a;
        return new Bool(a_bool.bool=="False" ? "True" : "False");
      }
      
      return new Not(a);
    }

public String toString() {
  return String.format("Not(%1$s)", arg.toString());
}

public String toAssembly() { return arg.toAssembly() + "\nNot";}

}	 
