public class Equal extends ExpressionA_Binaire {

public Equal (ExpressionA gauche,ExpressionA droite) {super(gauche, droite);}
    
public String symbole() {return "Equal";}; 

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
        return new Bool(g_num.num==d_num.num ? "True" : "False");
      }
      
      if(g instanceof Bool && d instanceof Bool)
      {
        Bool g_bool = (Bool) g;
        Bool d_bool = (Bool) d;
        return new Bool(g_bool.bool==d_bool.bool ? "True" : "False");
      }
      
      return new Equal(g, d);
    }

public String toAssembly() {return gauche.toAssembly() + "\n" + droite.toAssembly() + "\nEqual";}

}

