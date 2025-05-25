public class And extends ExpressionA {
    private ExpressionA gauche, droite;
    
    public And(ExpressionA gauche, ExpressionA droite) {
        this.gauche = gauche;
        this.droite = droite;
        this.taille = gauche.getTaille() + droite.getTaille() + 3;
    }

    
    public ExpressionA simplifie() {
      ExpressionA g = gauche.simplifie();
      ExpressionA d = droite.simplifie();
      if(g instanceof Bool && d instanceof Bool)
      {
        Bool g_bool = (Bool) g;
        Bool d_bool = (Bool) d;
        if(g_bool.bool=="False" || d_bool.bool=="False")
        {
          return new Bool("False");
        }
        return new Bool("True");
      }
      
      return new And(g, d);
    }
    
    @Override
    public String toAssembly() {
        return gauche.toAssembly() 
            + "\nConJmp " + (droite.getTaille() + 1)
            + "\n" + droite.toAssembly() 
            + "\nJump 1"
            + "\nCsteBo False";
    }
}
