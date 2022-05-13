import java.util.Comparator;

public class ComparaComentarios implements Comparator<User> {
    @Override
    public int compare(User a, User o) {
        if(a.getNumeroDeComentarios() < o.getNumeroDeComentarios()){
            return -1;
        }
        if(a.getNumeroDeComentarios() > o.getNumeroDeComentarios()){
            return 1;
        }
        return 0;
        }
    
}
