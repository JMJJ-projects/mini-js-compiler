public class Empty extends ExpressionA {
    public Empty() {
        this.taille = 0;
    }
    
    @Override
    public String toAssembly() {
        return "";
    }
}
