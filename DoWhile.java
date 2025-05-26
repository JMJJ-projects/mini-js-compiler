public class DoWhile extends ExpressionA {
    private ExpressionA body;
    private ExpressionA condition;
    
    public DoWhile(ExpressionA body, ExpressionA condition) {
        this.body = body;
        this.condition = condition;
        this.taille = body.getTaille() + condition.getTaille() + 2;
    }
    
    public ExpressionA simplifie() {
      ExpressionA b = body.simplifie();
      ExpressionA c = condition.simplifie();
      if(c instanceof Bool)
      {
        Bool c_bool = (Bool) c;
        if(c_bool.bool=="False")
        {
          return b;
        }
      }
      return new DoWhile(b, c);
    }

    @Override
    public String toAssembly() {
        int bodySize = body.getTaille();
        int condSize = condition.getTaille();
        int jumpOffset = -(bodySize + condSize + 2);
        
        return body.toAssembly() 
            + "\n" + condition.toAssembly() 
            + "\nConJmp 1"
            + "\nJump " + jumpOffset;
    }
}
