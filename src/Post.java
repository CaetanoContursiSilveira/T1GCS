import java.time.LocalDateTime;
import java.util.ArrayList; 
public class Post{
    private User autor;
    private String texto;
    private ArrayList <String> tags = new ArrayList <String>(); 
    private LocalDateTime data;
    private String link;
    private ArrayList <Comentario> comentarios = new ArrayList <Comentario>();

    public Post(User a, String t){
        this.autor = a;
        this.texto = t;
        this.data = LocalDateTime.now();
    }

    public User getAutor(){
        return autor;
    }
    
    public String getTexto(){
        return texto;
    }

    public LocalDateTime getData(){
        return data;
    }

    public String getTags(){
        if(tags.isEmpty()) return "Não há tags.\n";
        return String.join(", ",tags);
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public void AdicionarTags(String t){
        tags.add(t);
    }

    public void AdicionarLink(String l){
        if(l.startsWith("http://")|| l.startsWith("https://")) link = l;
        else System.out.println("Link inválido.\n");
    }

    public void AdicionarComentario(Comentario c){
        comentarios.add(c);
    }

    public void RemoverComentario(int i){
        comentarios.remove(i);
    }

    public String toString() {
        return String.format("Autor: %s\nData: %s\nTexto: %s\nTags: %s", autor.getNome(), data, texto, getTags());
    }
}