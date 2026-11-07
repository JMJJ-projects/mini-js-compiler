import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * import foo  — inlines the already-compiled module foo.jsm (without its final Halt).
 * The module is read once in the constructor so that its size is known when the
 * enclosing if/while computes its jump offsets.
 */
public class Import extends ExpressionA {
  public String id;
  private final String code;

  public Import(String id) {
    this.id = id;
    String loaded;
    try {
      List<String> lignes = Files.readAllLines(Paths.get(id + ".jsm"));
      while (!lignes.isEmpty() && (lignes.get(lignes.size() - 1).trim().isEmpty()
             || lignes.get(lignes.size() - 1).trim().equals("Halt"))) {
        lignes.remove(lignes.size() - 1);
      }
      loaded = String.join("\n", lignes);
      this.taille = lignes.size();
    } catch (IOException e) {
      loaded = "Error import_introuvable_" + id;
      this.taille = 1;
    }
    this.code = loaded;
  }

  public ExpressionA simplifie() {
    return this;
  }

  public String toString() {
    return "import " + this.id;
  }

  public String toAssembly() {
    return code;
  }
}
