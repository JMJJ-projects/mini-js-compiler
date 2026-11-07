import java.util.List;
public class Sequence extends ExpressionA {
    private List<ExpressionA> expressions;
    
    public Sequence(List<ExpressionA> expressions) {
        this.expressions = expressions;
        this.taille = expressions.stream().mapToInt(ExpressionA::getTaille).sum();
    }
    
    public ExpressionA simplifie() {
      List<ExpressionA> simplified = new java.util.ArrayList<>();
      for (ExpressionA e : expressions) {
        simplified.add(e.simplifie());
      }
      return new Sequence(simplified);
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
