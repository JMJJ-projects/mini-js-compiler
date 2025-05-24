public class GrSt extends ExpressionA_Binaire {

public GrSt (ExpressionA gauche,ExpressionA droite) {super(gauche, droite);}
    
public String symbole() {return "SupSt";}; 

public String toAssembly() {return gauche.toAssembly() + "\n" + droite.toAssembly() + "\nGrStsNb";}

}

