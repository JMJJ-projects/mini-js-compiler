public class Mult extends ExpressionA_Binaire {

public Mult(ExpressionA gauche,ExpressionA droite) {super(gauche, droite);}

public String symbole() {return "fois";}; 

public ExpressionA simplifie() {
      ExpressionA g = gauche.simplifie();
      ExpressionA d = droite.simplifie();
      if(g instanceof Num && d instanceof Num)
      {
        Num g_num = (Num) g;
        Num d_num = (Num) d;
      
        if(g_num.nan == "NaN" || d_num.nan == "NaN")
        {
          return new Num("NaN");
        }
        double val = ((Num) g).num * ((Num) d).num;
        return new Num(val);
      }
      return new Mult(g, d);
    }

public String toAssembly() {return gauche.toAssembly() + "\n" + droite.toAssembly() + "\nMultNb";}

}

