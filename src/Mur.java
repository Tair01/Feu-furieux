/**
 * La classe Mur représente une case traversable spécifique qui agit comme un mur.
 * Les murs ne sont pas traversables et n'ont pas de clés.
 */
public class Mur extends CaseTraversable {

    /**
     * Constructeur de la classe Mur.
     * @param l La ligne de la case.
     * @param c La colonne de la case.
     */
    public Mur(int l, int c) {
        super(l, c, 0);
    }

    @Override
    public boolean estTraversable() {
        return false;
    }

    @Override
    public boolean haveCles() {
        return false;
    }
}
