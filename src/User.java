import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private static final AtomicInteger count = new AtomicInteger(0);// auto incrementa o id, começa no 0;
    private int id;
    private String nome;
    private boolean tipo;// true = adm
    private int numeroDePostagens;

    public User(String nome, boolean tipo) {
        this.nome = nome;
        this.tipo = tipo;
        this.id = count.incrementAndGet();
        this.numeroDePostagens = 0;
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
}