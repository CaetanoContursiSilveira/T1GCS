import java.util.concurrent.atomic.AtomicInteger;

public class User implements Comparable<User>{
    private static final AtomicInteger count = new AtomicInteger(0);// auto incrementa o id, começa no 0;
    private int id;
    private String nome;
    private boolean tipo;// true = adm
    private int numeroDePostagens;
    private int numeroDeComentarios;

    public int getNumeroDeComentarios(){
        return numeroDeComentarios;
    }

    public User(String nome, boolean tipo) {
        this.nome = nome;
        this.tipo = tipo;
        this.id = count.incrementAndGet();
        this.numeroDePostagens = 0;
        this.numeroDeComentarios = 0;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        if (tipo)
            return "Administrador.\n";
        return "Funcionário.\n";
    }

    public boolean eadm() {
        return tipo;
    }

    public void setNome(String n) {
        nome = n;
    }

    public void setTipo(boolean t) {
        tipo = t;
    }

    public int getNumeroDePostagens() {
        return numeroDePostagens;
    }

    public void novaPostagem() {
        numeroDePostagens += 1;
    }

    public void apagouPostagem() {
        numeroDePostagens -= 1;
    }

    public void novoComentario() {
        numeroDeComentarios += 1;
    }

    public void apagouComentario() {
        numeroDeComentarios -= 1;
    }

    @Override
    public int compareTo(User o) {
        if (this.numeroDePostagens < o.numeroDePostagens) {
            return -1;
        }
        if (this.numeroDePostagens > o.numeroDePostagens) {
            return 1;
        }
        return 0;
    }


}