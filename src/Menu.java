import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Menu {
    private ArrayList<Post> todasPostagens;
    private User usuarioAtivo;
    private BufferedReader reader;

    public Menu(BufferedReader reader) {
        this.todasPostagens = new ArrayList<>();
        this.reader = reader;
        this.usuarioAtivo = new User("Jose",false);
    }
    public void criarNovaPostagem() {
        try {

            System.out.println("Digite o texto da postagem");
            String texto = reader.readLine();

            Post novo = new Post(usuarioAtivo, texto);

            todasPostagens.add(novo);
        } catch (IOException erro) {
            System.out.println("Erro ao processar entrada");
        }


    }
}
