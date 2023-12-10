/**
 * La classe Sortie représente une case traversable spécifique qui agit comme une sortie.
 * Les sorties sont toujours traversables, et elles n'ont pas de clés.
 */
public class Sortie extends CaseTraversable {

    /**
     * Constructeur de la classe Sortie.
     * @param l   La ligne de la case.
     * @param c   La colonne de la case.
     * @param feu Le niveau d'en feu initial de la case.
     */
    public Sortie(int l, int c, int feu) {
        super(l, c, feu);
    }

    @Override
    public boolean estTraversable() {
        return true;
    }

    @Override
    public void entre(Joueur j) {
        super.entre(j);
    }

    @Override
    public boolean haveCles() {
        return false;
    }
}

