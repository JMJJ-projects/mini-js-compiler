import java.util.List;
public class Sequence extends ExpressionA {
    private List<ExpressionA> expressions;
    
    public Sequence(List<ExpressionA> expressions) {
        this.expressions = expressions;
        this.taille = expressions.stream().mapToInt(ExpressionA::getTaille).sum();
    }
    
    public ExpressionA simplifie() {
      return this;
    }
    
    @Override
    public String toAssembly() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expressions.size(); i++) {
            sb.append(expressions.get(i).toAssembly());
            if ((i < expressions.size() - 1)) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
