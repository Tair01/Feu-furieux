/**
 * La classe abstraite CaseTraversable étend la classe Case et représente une cellule
 * traversable dans un tableau bidimensionnel. Elle inclut des fonctionnalités spécifiques
 * aux cases traversables comme la possibilité d'avoir un joueur, d'être en feu, etc.
 */
public abstract class CaseTraversable extends Case {

    private Joueur j;
    private int enFeu;

    /**
     * Constructeur de la classe CaseTraversable.
     * @param l   La ligne de la case.
     * @param c   La colonne de la case.
     * @param feu Le niveau initial d'en feu de la case.
     */
    public CaseTraversable(int l, int c, int feu) {
        super(l, c);
        this.j = null;
        this.enFeu = feu;
    }

    // Méthodes spécifiques aux cases traversables

    public int getEnFeu() {
        return enFeu;
    }

    public void mettreEnFeu() {
        if (10 > enFeu) {
            enFeu++;
        }
    }

    public void eteindreEnFeu() {
        if (enFeu > 0) {
            enFeu--;
        }
    }

    @Override
    public boolean estTraversable() {
        return true; // Comme c'est une case traversable, c'est toujours true
    }

    public boolean haveJ() {
        return j != null; // Est-ce qu'il y a un joueur dans la case ou pas
    }

    public abstract boolean haveCles(); // Est-ce qu'il y a une clé dans la case

    public void vide() {
        j = null;
    }

    public void entre(Joueur j) {
        this.j = j;
        j.attackDuFeu(enFeu);
    }

    @Override
    public void ajouterRes(int c) {
        int a = j.getResistance();
        a = a - c;
        if (a < 0) {
            j.setResistance(0);
        }
    }

    public void ajouterChaleur(int ch) {
        chaleur += ch;
        chaleur = Math.max(0, Math.min(10, chaleur));
    }
}

