public class And extends ExpressionA {
    private ExpressionA gauche, droite;
    
    public And(ExpressionA gauche, ExpressionA droite) {
        this.gauche = gauche;
        this.droite = droite;
        this.taille = gauche.getTaille() + droite.getTaille() + 3;
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
