public class IfElse extends ExpressionA {
    private ExpressionA condition;
    private ExpressionA thenBlock;
    private ExpressionA elseBlock;
    
    public IfElse(ExpressionA condition, ExpressionA thenBlock, ExpressionA elseBlock) {
        this.condition = condition;
        this.thenBlock = thenBlock;
        this.elseBlock = elseBlock;
        this.taille = condition.getTaille() + thenBlock.getTaille() + elseBlock.getTaille() + 2;
    }
    
     public ExpressionA simplifie() {
      ExpressionA cond = condition.simplifie();
      ExpressionA then = thenBlock.simplifie();
      ExpressionA else_ = elseBlock.simplifie();
      if(cond instanceof Bool)
      {
        Bool c_bool = (Bool) cond;
        if(c_bool.bool=="True")
        {
          return then;
        }
        return else_;
      }
      return new IfElse(cond, then, else_);
    }
    
    @Override
    public String toAssembly() {
    	String notempty;
    	if(thenBlock.getTaille() == 0) {
    		notempty = "";
    	}
    	else {
    		notempty = "\n" + thenBlock.toAssembly();
    	}
        int conJmpOffset = thenBlock.getTaille() + 1;
        int jumpOffset = elseBlock.getTaille();
        
        return condition.toAssembly() 
            + "\nConJmp " + conJmpOffset 
            + notempty
            + "\nJump " + jumpOffset 
            + "\n" + elseBlock.toAssembly();
    }
}
