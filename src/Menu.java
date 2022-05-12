import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Menu {
    private ArrayList<Post> todasPostagens;
    private ArrayList<User> todosUsuarios;
    private User usuarioAtivo;
    private BufferedReader reader;

    public Menu(BufferedReader reader) {
        this.todasPostagens = new ArrayList<>();
        this.todosUsuarios = new ArrayList<>();
        this.reader = reader;
        this.usuarioAtivo = null;
    }

    public void escolherUsuario() {
        try {
            System.out.println("Escolha um usuário");
            for (User usuario : todosUsuarios) {
                System.out.println(usuario.getNome());
            }
            String user = "";
            boolean escolheu = false;
            while (escolheu == false) {
                user = reader.readLine();
                for (User usuario : todosUsuarios) {
                    if (user.equals(usuario.getNome())) {
                        this.usuarioAtivo = usuario;
                        escolheu = true;
                        System.out.println("Usuário " + user + " escolhido!");
                    }
                }
                if (escolheu == false) {
                    System.out.println("Usuário inválido, digite novamente");
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao processar entrada");
        }
    }

    public void criarNovaPostagem() {
        try {
            // só postar se tiver usuario
            if (usuarioAtivo != null) {
                System.out.println("Digite o texto da postagem:");
                String texto = reader.readLine();

                Post novo = new Post(usuarioAtivo, texto);

                System.out.println("Digite as tags da postagem (separados por vírgula):");
                String tags = reader.readLine();
                List<String> list = Arrays.asList(tags.split(","));
                novo.setTags(new ArrayList<>(list));

                System.out.println("Digite um link para postagem (opcional):");
                String link = reader.readLine();

                while (!(link.isBlank() || link.startsWith("http://") || link.startsWith("https://"))) {
                    System.out.println(
                            "Entrada invalida, link deve começar por http ou https. Se não deseja link, deixar em branco");
                    link = reader.readLine();
                }
                novo.AdicionarLink(link);
                todasPostagens.add(novo);
            } else {
                System.out.println("Escolha um usuário antes de postar algo");
            }
        } catch (IOException erro) {
            System.out.println("Erro ao processar entrada");
        }

    }

public void listarPostagens() {
    if (todasPostagens.size() != 0) {
        for (Post postagem : todasPostagens) {
            System.out.println("\n---------------------------------");
            System.out.println(postagem);
        }
    } else {
        System.out.println("Nenhuma postagem ainda");
    }

}
public void criarNovoUsuario() {
    try {
        System.out.print("Digite o nome do usuário: ");
        String nome = reader.readLine();

        System.out.println(
                "Digite T para solicitar privilégios de administrador, caso contrário, deixe em branco");
        String adm = reader.readLine();
        boolean isAdm = false;
        while (!adm.equals("T")) {
            if (adm.equals("T")) {
                isAdm = true;
            } else {
                System.out.println("Entrada inválida, digite novamente");
                adm = reader.readLine();
            }
        }
        todosUsuarios.add(new User(nome, isAdm));
        System.out.println("Usuário " + nome + " criado!");
    } catch (Exception e) {
        System.out.println("Erro ao processar entrada");
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

    public void usuariosPadroes() {
        todosUsuarios.add(new User("joao", true));
        todosUsuarios.add(new User("pedro", false));
        todosUsuarios.add(new User("lucas", true));
        todosUsuarios.add(new User("matheus", false));
        todosUsuarios.add(new User("nicolas", true));
    }

    public void postsPadroes() {
        todasPostagens.add(new Post(todosUsuarios.get(0), "post do joao 1"));
        todasPostagens.add(new Post(todosUsuarios.get(0), "post do joao 2"));
        todasPostagens.add(new Post(todosUsuarios.get(0), "post do joao 3"));
        todasPostagens.add(new Post(todosUsuarios.get(1), "post do pedro 1"));
        todasPostagens.add(new Post(todosUsuarios.get(1), "post do pedro 2"));
        todasPostagens.add(new Post(todosUsuarios.get(1), "post do pedro 3"));
        todasPostagens.add(new Post(todosUsuarios.get(2), "post do lucas 1"));
        todasPostagens.add(new Post(todosUsuarios.get(2), "post do lucas 2"));
        todasPostagens.add(new Post(todosUsuarios.get(2), "post do lucas 3"));
        todasPostagens.add(new Post(todosUsuarios.get(3), "post do matheus 1"));
        todasPostagens.add(new Post(todosUsuarios.get(3), "post do matheus 2"));
        todasPostagens.add(new Post(todosUsuarios.get(3), "post do matheus 3"));
        todasPostagens.add(new Post(todosUsuarios.get(4), "post do nicholas 1"));
        todasPostagens.add(new Post(todosUsuarios.get(4), "post do nicholas 2"));
        todasPostagens.add(new Post(todosUsuarios.get(4), "post do nicholas 3"));
    }

}
