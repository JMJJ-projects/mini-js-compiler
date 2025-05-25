public class And extends ExpressionA {
    private ExpressionA gauche, droite;
    
    public And(ExpressionA gauche, ExpressionA droite) {
        this.gauche = gauche;
        this.droite = droite;
        this.taille = gauche.getTaille() + droite.getTaille() + 3;
    }
    
    @Override
    public String toAssembly() {
    	String notempty;
    	if(droite.getTaille() == 0) {
    		notempty = "";
    	}
    	else {
    		notempty = "\n" + droite.toAssembly();
    	}
        return gauche.toAssembly() 
            + "\nConJmp " + (droite.getTaille() + 2)
            + notempty
            + "\nJump 1"
            + "\nCsteBo 0";
    }
}
