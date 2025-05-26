public class Empty extends ExpressionA {
    public Empty() {
        this.taille = 0;
    }
    
    public ExpressionA simplifie() {
      return this;
    }
    
    @Override
    public String toAssembly() {
        return "";
    }
}
