public class GrEq extends ExpressionA_Binaire {

public GrEq (ExpressionA gauche,ExpressionA droite) {super(gauche, droite);}

public ExpressionA simplifie() {
      ExpressionA g = gauche.simplifie();
      ExpressionA d = droite.simplifie();
      if(g instanceof Num && d instanceof Num)
      {
        Num g_num = (Num) g;
        Num d_num = (Num) d;
      
        if(g_num.nan == "NaN" || d_num.nan == "NaN")
        {
          return new Bool("False");
        }
        return new Bool(g_num.num>=d_num.num ? "True" : "False");
      }
      return new GrEq(g, d);
    }
    
public String symbole() {return "SupEg";}; 

public String toAssembly() {return gauche.toAssembly() + "\n" + droite.toAssembly() + "\nGrEqNb";}
}

