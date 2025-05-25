// non disponible hors du package (non publique).
// Permet uniquement de factoriser le code des noeuds binaires.
public abstract class ExpressionA_Binaire extends ExpressionA {
  ExpressionA gauche;
  ExpressionA droite;

  ExpressionA_Binaire (ExpressionA gauche,ExpressionA droite) {
    this.gauche = gauche;
    this.droite = droite;
    this.taille = gauche.getTaille() + droite.getTaille() + 1;
  }

  abstract String symbole(); 

  public String toString() {
    return String.format("%1$s(%2$s,%3$s)", symbole(), gauche.toString(), droite.toString());
  }
}

