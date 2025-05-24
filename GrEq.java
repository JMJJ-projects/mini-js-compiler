public class GrEq extends ExpressionA_Binaire {

public GrEq (ExpressionA gauche,ExpressionA droite) {super(gauche, droite);}
    
public String symbole() {return "SupEg";}; 

public String toAssembly() {return gauche.toAssembly() + "\n" + droite.toAssembly() + "\nGrEqNb";}
}

