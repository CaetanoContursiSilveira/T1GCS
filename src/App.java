import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class App {

    private static ArrayList<Post> postagensInicial() {
        User user = new User("John Doe", false);
        return new ArrayList<Post>() {
            {
                add(new Post(1, user, "Texto da Postagem 1"));
                add(new Post(2, user, "Texto da Postagem 2"));
                add(new Post(3, user, "Texto da Postagem 3"));
            }
        };
    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        Menu menu = new Menu(reader, postagensInicial());


        System.out.println("Bem Vindo ao Blog Interno de T1GCS, digite o número da opção desejada:");
        System.out.println("1 - Escolher usuário ativo");
        System.out.println("2 - Criar uma nova postagem");
        System.out.println("3 - Listar postagens");
        System.out.println("4 - Excluir postagem");
        System.out.println("5 - Criar comentário");
        System.out.println("6 - Sair");

        // Reading data using readLine
        Integer opcao = Integer.parseInt(reader.readLine());
        while (opcao != 6) {
            switch (opcao) {
                case 2:
                    menu.criarNovaPostagem();
                    break;
                case 3:
                    menu.listarPostagens();
                    break;
                case 5:
                    menu.criarComentario();
                    break;
            }
            System.out.println("Digite nova opção:");
            opcao = Integer.parseInt(reader.readLine());
        }


        
    }
}
