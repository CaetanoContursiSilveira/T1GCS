import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        Menu menu = new Menu(reader);

        System.out.println("Bem Vindo ao Blog Interno de T1GCS, digite o número da opção desejada:");
        System.out.println("1 - Escolher usuário ativo");
        System.out.println("2 - Criar uma nova postagem");
        System.out.println("3 - Listar postagens");
        System.out.println("4 - Excluir postagem");
        System.out.println("5 - Sair");

        // Reading data using readLine
        Integer opcao = 0;
        while (opcao != 5) {
            try {
                opcao = Integer.parseInt(reader.readLine());
                switch (opcao) {
                    case 1:
                        menu.escolherUsuario();
                    case 2:
                        menu.criarNovaPostagem();
                        break;
                    case 3:
                        menu.listarPostagens();
                        break;
                }
                System.out.println("Digite nova opção:");
            } catch (Exception e) {
                System.out.println("Opção inválida, digite nova opção:");
            }
        }

    }
}
