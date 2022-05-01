import java.util.concurrent.atomic.AtomicInteger;
public class User{
    private static final AtomicInteger count = new AtomicInteger(0);//auto incrementa o id, começa no 0;
    private int id;
    private String nome;
    private boolean tipo;//true = adm

    public User(String n, boolean t){
        this.nome = n;
        this.tipo = t;
        this.id = count.incrementAndGet();
    }

    public String getNome(){
        return nome;
    }

    public String getTipo(){
        if(tipo) return "Administrador.\n";
        return "Funcionário.\n";
    }

    public boolean eadm(){
        return tipo;
    }

    public void setNome(String n){
        nome = n;
    }

    public void setTipo(boolean t){
        tipo = t;
    }
}