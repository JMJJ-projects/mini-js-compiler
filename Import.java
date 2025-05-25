public class Import extends ExpressionA {
  public String id;
    
  public Import(String id) {
    this.id = id;
  }
  
  public String toString() {
    try{
      java.nio.file.Path fichier = java.nio.file.Paths.get(this.id + ".jsm");
      return java.nio.file.Files.readString(fichier);
    }
    catch(java.io.IOException e)
    {
      return "erreur d'importation " + this.id;
    }
  }

}
