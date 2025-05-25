import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Import extends ExpressionA {
  public String id;
    
  public Import(String id) {
    this.id = id;
  }
  
  public String toString() {
    return "import " + this.id; 
  }
  
  public String toAssembly() {
    try{
      Path fichier = Paths.get(this.id + ".jsm");
      List<String> lignes = Files.readAllLines(fichier);
      if(!lignes.isEmpty())
      {
        lignes.remove(lignes.size()-1);
      }
      return String.join("\n", lignes);
    }
    catch(java.io.IOException e)
    {
      return "erreur d'importation " + this.id;
    }
  }

}
