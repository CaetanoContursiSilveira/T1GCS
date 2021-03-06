import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Menu {
    private ArrayList<Post> todasPostagens;
    private ArrayList<User> todosUsuarios;
    private ArrayList<String> palavrasProibidas;
    private ArrayList<Post> logPostagensProibidas;
    private User usuarioAtivo;
    private BufferedReader reader;

    public Menu(BufferedReader reader) {
        this.todasPostagens = new ArrayList<>();
        this.todosUsuarios = new ArrayList<>();
        this.palavrasProibidas = new ArrayList<>();
        this.logPostagensProibidas = new ArrayList<>();
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
                String texto = "";

                boolean permitido = false;
                while (!permitido) {
                    System.out.println("Digite o texto da postagem:");
                    texto = reader.readLine();
                    for (String palavra : palavrasProibidas) {
                        if (!texto.toLowerCase().contains(palavra.toLowerCase())) {
                            permitido = true;
                        } else {
                            System.out.println("A palavra " + palavra + " não é permitida");
                            Post proibido = new Post((todasPostagens.size() + 1), usuarioAtivo, texto);
                            logPostagensProibidas.add(proibido);
                            return;
                        }
                    }
                }

                Post novo = new Post((todasPostagens.size() + 1), usuarioAtivo, texto);
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
                usuarioAtivo.novaPostagem();
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

            Post postagem = todasPostagens.get(id - 1);
            System.out.println("Digite o seu comentario:");
            String texto = reader.readLine();

            Comentario comentario = new Comentario(usuarioAtivo, texto);
            postagem.AdicionarComentario(comentario);
            usuarioAtivo.novoComentario();

        } catch (IOException erro) {
            System.out.println("Erro ao processar entrada");
        }
    }

    public void excluirPost() {
        try {
            System.out.println("Escolha a postagem que deseja excluir");
            Integer opcao = Integer.parseInt(reader.readLine());

            if (usuarioAtivo.eadm()) {
                todasPostagens.remove(opcao - 1);
                todasPostagens.get(opcao - 1).getAutor().apagouPostagem();
                System.out.println("Post " + (opcao - 1) + " removido");
            }

            if (!usuarioAtivo.eadm()) {
                if (todasPostagens.get(opcao - 1).getAutor().equals(usuarioAtivo)) {
                    todasPostagens.remove(opcao - 1);
                    usuarioAtivo.apagouPostagem();
                    System.out.println("Post " + (opcao) + " removido");
                } else {
                    System.out.println("Você não tem permissão para deletar esse post");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao processar entrada ou usuário não selecionado");
        }
    }

    public void adicionarPalavraProibida() {
        try {
            if (usuarioAtivo.eadm()) {
                System.out.println("Digite a palavra proibida");
                String palavra = reader.readLine();
                palavrasProibidas.add(palavra);
                System.out.println(palavra + " agora é uma palavra proibida!");
            } else {
                System.out.println("Você não possui permissão para adicionar palavras proibidas");
            }
        } catch (Exception e) {
            System.out.println("Erro ao processar entrada ou usuário não selecionado");
        }
    }

    public void removerPalavraProibida() {
        try {
            if (usuarioAtivo.eadm()) {
                System.out.println("Digite a palavra proibida que deseja remover da seguinte lista:\n");
                listarPalavrasProibidas();
                String palavra = reader.readLine();
                palavrasProibidas.remove(palavra);
                System.out.println(palavra + " não é mais uma palavra proibida!");
            } else {
                System.out.println("Você não possui permissão para remover palavras proibidas");
            }
        } catch (Exception e) {
            System.out.println("Erro ao processar entrada ou usuário não selecionado");
        }
    }

    public void listarPalavrasProibidas() {
        for (String palavra : palavrasProibidas) {
            System.out.println(palavra);
        }
    }

    public void verLogPostagensProibidas() {
        try {
            if (usuarioAtivo.eadm()) {
                if (logPostagensProibidas.size() != 0) {
                    for (Post postagem : logPostagensProibidas) {
                        System.out.println("\n---------------------------------");
                        System.out.println(postagem);
                    }
                } else {
                    System.out.println("Nenhuma postagem ainda");
                }
            } else {
                System.out.println("Você não tem permissao para ver os logs");
            }
        } catch (Exception e) {
            System.out.println("Erro ao processar entrada ou usuário não selecionado");
        }
    }

    public void buscarPorPostagem() {
        try {
            System.out.println("digite o número da opção desejada:");
            System.out.println("1 - Buscar por texto");
            System.out.println("2 - Buscar por tags");

            Integer opcao = Integer.parseInt(reader.readLine());
            switch (opcao) {
                case 1:
                    System.out.println("Digite a palavra que deseja procurar em algum post");
                    String palavra = reader.readLine();
                    if (!palavrasProibidas.contains(palavra)) {
                        for (Post postagem : todasPostagens) {
                            if (postagem.getTexto().toLowerCase().contains(palavra)) {
                                System.out.println(postagem);
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println("Digite a tag que deseja procurar em algum post");
                    String tag = reader.readLine();
                    if (!palavrasProibidas.contains(tag)) {
                        for (Post postagem : todasPostagens) {
                            for (String tags : postagem.getTags()) {
                                if (tag.equals(tags)) {
                                    System.out.println(postagem);
                                }
                            }
                        }
                    }
                    break;
            }
        } catch (Exception e) {
            System.out.println("Erro ao processar entrada");
        }
    }

    public void informacoesParaOAdm() {
        if (usuarioAtivo.eadm()) {
            int nDePosts = todasPostagens.size();
            int nDeComentarios = 0;
            for (Post post : todasPostagens) {
                nDeComentarios += post.getComentarios().size();
            }
            int nDeUsuarios = todosUsuarios.size();

            System.out.println("Numero de posts: " + nDePosts);
            System.out.println("Numero de comentários: " + nDeComentarios);
            System.out.println("Numero de usuários: " + nDeUsuarios);
        } else {
            System.out.println("Você não possui permissão para acessar esses dados");
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
        todasPostagens.add(new Post(1, todosUsuarios.get(0), "post do joao 1"));
        todasPostagens.add(new Post(2, todosUsuarios.get(0), "post do joao 2"));
        todasPostagens.add(new Post(3, todosUsuarios.get(0), "post do joao 3"));
        todasPostagens.add(new Post(6, todosUsuarios.get(1), "post do pedro 1"));
        todasPostagens.add(new Post(9, todosUsuarios.get(2), "post do lucas 1"));
        todasPostagens.add(new Post(10, todosUsuarios.get(3), "post do matheus 1"));
        todasPostagens.add(new Post(13, todosUsuarios.get(4), "post do nicholas 1"));
    }

    public void palavrasProibidasPadroes() {
        palavrasProibidas.add("proibida1");
        palavrasProibidas.add("proibida2");
        palavrasProibidas.add("proibida3");
    }

    public void UsuariosComMaisPostagens(){
        List<User> ordenada = todosUsuarios;
        Collections.sort(ordenada);
        List <User> top5 = ordenada.subList(0, 4);
        int i = 1;
        for (User user : top5) {
            System.out.println(i +  "º " + user.getNome());
            i++;
        }

    }

    public void PostagensMaisComentadas(){
        List<Post> ordenada = todasPostagens;
        Collections.sort(ordenada);
        int i = 1;
        List <Post> top5 = ordenada.subList(0, 4);
        for (Post post : top5) {
            System.out.println(i + "º " + post.sumario());
            i++;
        }     
    }

    public void UsuariosComMaisComentarios(){
        List<User> ordenada = todosUsuarios;
        Collections.sort(ordenada,new ComparaComentarios());
        int i = 1;
        List<User> top10 = ordenada.subList(0, 9);
        for (User user : top10) {
            System.out.println(i + "º " + user.getNome());
            i++;
        }
    }



}