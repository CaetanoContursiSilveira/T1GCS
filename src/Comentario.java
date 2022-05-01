import java.time.LocalDateTime;
public class Comentario{
    private String texto;
    private User autor;
    private LocalDateTime data;

    public Comentario(User a, String t){
        this.autor = a;
        this.texto = t.substring(0,100);
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
    
}