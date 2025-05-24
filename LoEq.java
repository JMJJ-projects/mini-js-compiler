public class LoEq extends ExpressionA_Binaire {

public LoEq (ExpressionA gauche,ExpressionA droite) {super(gauche, droite);}
    
public String symbole() {return "InfEg";}; 

public String toAssembly() {return gauche.toAssembly() + "\n" + droite.toAssembly() + "\nLoEqNb";}

}

