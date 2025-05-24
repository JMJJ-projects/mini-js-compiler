public class LoSt extends ExpressionA_Binaire {

public LoSt (ExpressionA gauche,ExpressionA droite) {super(gauche, droite);}
    
public String symbole() {return "InfSt";}; 

public String toAssembly() {return gauche.toAssembly() + "\n" + droite.toAssembly() + "\nLoStNb";}
}

