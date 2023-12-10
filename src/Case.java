/**
 * La classe abstraite Case représente une cellule dans un tableau bidimensionnel.
 * Chaque case a une position spécifique définie par ses coordonnées (ligne, colonne),
 * ainsi que des propriétés associées comme la chaleur.
 */
public abstract class Case  {
    /** Coordonnée de la ligne,colonne de la case dans le tableau. */
    public final int lig, col;
    /** Niveau de chaleur de la case. */
    public int chaleur;
    /**
     * Constructeur de la classe Case.
     * @param l La ligne de la case.
     * @param c La colonne de la case.
     */
    public Case(int l, int c) {
        this.lig = l;
        this.col = c;
        this.chaleur = 0;
    }
    // Méthodes abstraites à implémenter dans les classes dérivées

    /**
     * Vérifie si la case est traversable.
     * @return true si la case est traversable, false sinon.
     */
    public abstract boolean estTraversable();

    /**
     * Ajoute une quantité m de ressources à la case.
     * @param m La quantité  de ressources à ajouter.
     */
    public abstract void ajouterRes(int m);
    /**
     * Récupère le niveau de chaleur en cours de la case.
     * @return Le niveau de chaleur de la case.
     */
    public abstract int getEnFeu();
    /**
     * Met la case en état de feu.
     */
    public abstract void mettreEnFeu();
    /**
     * Éteint le feu de la case.
     */
    public abstract void eteindreEnFeu();
    /**
     * Ajoute une quantité de chaleur à la case.
     * @param ch La quantité de chaleur à ajouter.
     */
    public abstract void  ajouterChaleur(int ch);
    /**
     * Récupère le niveau de chaleur de la case.
     * @return Le niveau de chaleur actuel de la case.
     */
    public int getChaleur(){return chaleur;}
}
