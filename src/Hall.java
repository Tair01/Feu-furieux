/**
 * La classe Hall représente une case traversable spécifique qui agit comme un hall.
 * Les halls peuvent contenir des clés, et le joueur peut prendre une clé lorsqu'il entre dans un hall
 * qui en contient une.
 */
public class Hall extends CaseTraversable {

    private boolean cle; // Indique si le hall contient une clé

    /**
     * Constructeur de la classe Hall.
     * @param l   La ligne de la case.
     * @param c   La colonne de la case.
     * @param f   Le niveau d'en feu initial de la case.
     */
    public Hall(int l, int c, int f) {
        super(l, c, f);
    }

    /**
     * Constructeur de la classe Hall.
     * @param l  La ligne de la case.
     * @param c  La colonne de la case.
     * @param cl Indique si le hall contient une clé.
     */
    public Hall(int l, int c, boolean cl) {
        super(l, c, 0);
        this.cle = cl;
    }

    /**
     * Constructeur de la classe Hall.
     * @param l La ligne de la case.
     * @param c La colonne de la case.
     */
    public Hall(int l, int c) {
        this(l, c, false);
    }

    /**
     * Gère l'entrée d'un joueur dans le hall.
     * Si le hall contient une clé, le joueur la prend.
     * @param j Le joueur qui entre dans le hall.
     */
    public void entre(Joueur j) {
        super.entre(j);
        if (haveCles()) {
            j.prendCles();
            this.cle = false;
        }
    }

    /**
     * Vérifie si le hall contient une clé.
     * @return true si le hall contient une clé, false sinon.
     */
    public boolean haveCles() {
        return this.cle;
    }

    @Override
    public boolean estTraversable() {
        return true;
    }
}

