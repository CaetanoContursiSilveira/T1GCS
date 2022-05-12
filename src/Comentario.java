import java.time.LocalDateTime;
public class Comentario{
    private String texto;
    private User autor;
    private LocalDateTime data;

    public Comentario(User a, String t){
        this.autor = a;
        this.texto = t.substring(0,t.length() >= 100 ? 100 : t.length());
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

    public String toString() {
        return String.format("\n-----------\nComentário:\nAutor: %s\nData: %s\nTexto: %s\n", autor.getNome(), data, texto);
    }
    
}