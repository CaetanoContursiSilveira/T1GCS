import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        Menu menu = new Menu(reader);
        menu.usuariosPadroes();
        menu.postsPadroes();
        menu.palavrasProibidasPadroes();

        System.out.println("Bem Vindo ao Blog Interno de T1GCS, digite o número da opção desejada:");
        System.out.println("1 - Escolher usuário ativo");
        System.out.println("2 - Criar uma nova postagem");
        System.out.println("3 - Listar postagens");
        System.out.println("4 - Excluir postagem");
        System.out.println("5 - Criar novo usuário");
        System.out.println("6 - Criar novo comentário");
        System.out.println("7 - Buscar por postagem");
        System.out.println("8 - Adicionar palavra proibida");
        System.out.println("9 - Remover palavra proibida");
        System.out.println("10 - Ver log de posts proibidos");
        System.out.println("11 - Informações para o adm");
        System.out.println("12 - Top 5 usuários com mais postagens" );
        System.out.println("13 - Top 5 postagens mais comentadas");
        System.out.println("14 - Top 10 usuários com mais comentários");
        System.out.println("99 - Sair");

        // Reading data using readLine
        Integer opcao = 0;
        while (opcao != 99) {
            try {
                opcao = Integer.parseInt(reader.readLine());
                switch (opcao) {
                    case 1:
                        menu.escolherUsuario();
                        break;
                    case 2:
                        menu.criarNovaPostagem();
                        break;
                    case 3:
                        menu.listarPostagens();
                        break;
                    case 4:
                        menu.excluirPost();
                        break;
                    case 5:
                        menu.criarNovoUsuario();
                        break;
                    case 6:
                        menu.criarComentario();
                        break;
                    case 7:
                        menu.buscarPorPostagem();
                        break;
                    case 8:
                        menu.adicionarPalavraProibida();
                        break;
                    case 9:
                        menu.removerPalavraProibida();
                        break;
                    case 10:
                        menu.verLogPostagensProibidas();
                        break;
                    case 11:
                        menu.informacoesParaOAdm();
                        break;
                    case 12:
                        menu.UsuariosComMaisPostagens();
                        break;
                    case 13:
                        menu.PostagensMaisComentadas();
                        break;
                    case 14:
                        menu.UsuariosComMaisComentarios();
                        break;
                }
                if (opcao != 99) {
                    System.out.println("Digite nova opção:");

                }
            } catch (Exception e) {
                System.out.println("Opção inválida, digite nova opção:");

            }
        }
    }
}
