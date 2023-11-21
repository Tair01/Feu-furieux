public interface CaseTraversable {
    boolean estTraversable();
    void vide();
    void entre(Joueur j);
    Joueur getJ();
}
/*public class CaseTraversable extends Case {

    private Joueur joueur;

    public CaseTraversable(int l, int c) {
        super(l, c);
    }

    public Joueur getJ() {
        return joueur;
    }

    public void vide() {
        joueur = null;
    }

    public void entre(Joueur j) {
        joueur = j;
    }

    @Override
    public boolean estTraversable() {
        return true; // Une case traversable est toujours true
    }
}*/
