public class Divi extends ExpressionA_Binaire {

public Divi(ExpressionA gauche,ExpressionA droite) {super(gauche, droite);}

public String symbole() {return "div";}; 


public String toAssembly() {return gauche.toAssembly() + "\n" + droite.toAssembly() + "\nDiviNb";}

}

