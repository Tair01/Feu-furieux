/**
 * La classe Joueur représente un joueur qui peut se déplacer à travers des cases traversables,
 * collecter des clés, utiliser des clés pour ouvrir des portes, gérer sa résistance au feu, etc.
 */
public class Joueur {

    private CaseTraversable c;    // La case actuelle du joueur
    private int resistance;        // Niveau de résistance du joueur
    private int cles;              // Nombre de clés détenues par le joueur
    private boolean gagner;        // Indique si le joueur a gagné

    /**
     * Constructeur de la classe Joueur.
     * @param c La case initiale du joueur.
     * @param r Le niveau initial de résistance du joueur.
     * @param k Le nombre initial de clés détenu par le joueur.
     */
    public Joueur(CaseTraversable c, int r, int k) {
        this.c = c;
        this.resistance = r;
        this.cles = k;
        this.gagner = false;
    }

    public int getCles() {
        return cles;
    }

    public CaseTraversable getC() {
        return c;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int a) {
        resistance = a;
    }

    /**
     * Ajoute une clé à la collection du joueur.
     */
    public void prendCles() {
        cles++;
    }

    /**
     * Utilise une clé du joueur.
     */
    public void useCles() {
        cles--;
    }

    /**
     * Vérifie si le joueur a gagné.
     * @return true si le joueur a gagné, false sinon.
     */
    public boolean isGagner() {
        return gagner;
    }

    /**
     * Définit l'état de victoire du joueur.
     */
    public void gagner() {
        gagner = true;
    }

    /**
     * Gère l'attaque du feu sur le joueur.
     * @param feu Le niveau d'en feu à attaquer.
     */
    public void attackDuFeu(int feu) {
        resistance = resistance - feu;
        if (resistance < 0) {
            resistance = 0;
        }
    }

    /**
     * Effectue un déplacement du joueur vers une case cible.
     * @param cible La case vers laquelle le joueur se déplace.
     */
    public void bouge(Case cible) {
        if (cible.estTraversable()) {
            c.vide();
            c = (CaseTraversable) cible;
            c.entre(this);
        } else {
            // Si la cible est une porte et le joueur a des clés, utilise une clé et ouvre la porte
            if (cible instanceof Porte p && this.getCles() > 0) {
                useCles();
                p.ouvrirPorte();
            }
        }
        // Si la cible est une sortie, le joueur gagne
        if (cible instanceof Sortie) {
            this.gagner();
        }
    }
}
