import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu {
    private ArrayList<Post> todasPostagens;
    private User usuarioAtivo;
    private BufferedReader reader;

    public Menu(BufferedReader reader, ArrayList<Post> postagensInicial) {
        this.todasPostagens = postagensInicial;
        this.reader = reader;
        this.usuarioAtivo = new User("Jose",false);
    }
    public void criarNovaPostagem() {
        try {

            System.out.println("Digite o texto da postagem:");
            String texto = reader.readLine();

            Post novo = new Post(todasPostagens.size() + 1, usuarioAtivo, texto);

            System.out.println("Digite as tags da postagem (separados por vírgula:");
            String tags = reader.readLine();
            List<String> list = Arrays.asList(tags.split(","));
            novo.setTags(new ArrayList<>(list));

            System.out.println("Digite um link para postagem (opcional):");
            String link = reader.readLine();

            while(!(link.isBlank() || link.startsWith("http://")|| link.startsWith("https://"))) {
                System.out.println("Entrada invalida, link deve começar por http ou https. Se não deseja link, deixar em branco");
                link = reader.readLine();
            }
            novo.AdicionarLink(link);
            todasPostagens.add(novo);
        } catch (IOException erro) {
            System.out.println("Erro ao processar entrada");
        }


    }

    public void listarPostagens() {
        for (Post postagem : todasPostagens) {
            System.out.println("\n---------------------------------");
            System.out.println(postagem);
        }
    }

    private void listarSumarioPostagens() {
        for (Post postagem : todasPostagens) {
            System.out.println("\n---------------------------------");
            System.out.println(postagem.sumario());
        }
    }
    public void criarComentario() {
        try {
            listarSumarioPostagens();

            System.out.println("Digite o ID da postagem que deseja comentar:");
            int id = Integer.parseInt(reader.readLine());

            Post postagem = todasPostagens.get(id-1);
            System.out.println("Digite o seu comentario:");
            String texto = reader.readLine();

            Comentario comentario = new Comentario(usuarioAtivo, texto);
            postagem.AdicionarComentario(comentario);

        } catch (IOException erro) {
            System.out.println("Erro ao processar entrada");
        }
    }
}
