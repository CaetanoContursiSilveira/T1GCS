import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Post {
    private int id;
    private User autor;
    private String texto;
    private ArrayList<String> tags = new ArrayList<String>();
    private LocalDateTime data;
    private String link;
    private ArrayList<Comentario> comentarios = new ArrayList<Comentario>();

    public Post(int id, User a, String t) {
        this.id = id;
        this.autor = a;
        this.texto = t;
        this.data = LocalDateTime.now();
    }

    public User getAutor() {
        return autor;
    }

    public String getTexto() {
        return texto;
    }

    public LocalDateTime getData() {
        return data;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public void AdicionarTags(String t) {
        tags.add(t);
    }

    public void AdicionarLink(String link) {
        this.link = link;
    }

    public void AdicionarComentario(Comentario c) {
        comentarios.add(c);
    }

    public void RemoverComentario(int i) {
        comentarios.remove(i);
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public String toString() {
        return String.format("Id: %d\nAutor: %s\nData: %s\nTexto: %s\nTags: %s\nLink: %s\nComentários: %s", id,
                autor.getNome(), data, texto, getTags(), link, Arrays.toString(comentarios.toArray()));
    }

    public String sumario() {
        return String.format("Id: %d\nTexto: %s", id, texto);
    }
}