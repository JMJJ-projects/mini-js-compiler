public class Equal extends ExpressionA_Binaire {

public Equal (ExpressionA gauche,ExpressionA droite) {super(gauche, droite);}
    
public String symbole() {return "Equal";}; 

public String toAssembly() {return gauche.toAssembly() + "\n" + droite.toAssembly() + "\nEqual";}

}

