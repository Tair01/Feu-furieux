/**
 * La classe Porte représente une case traversable spécifique qui agit comme une porte.
 * Les portes peuvent être ouvertes ou fermées, et elles peuvent être traversées uniquement
 * si elles sont ouvertes. Les portes n'ont pas de clés.
 */
public class  Porte extends CaseTraversable{
    private boolean ouverte;
    /**
     * Constructeur de la classe Porte.
     * @param l    La ligne de la case.
     * @param c    La colonne de la case.
     * @param ouv  Indique si la porte est initialement ouverte.
     */
    public Porte(int l, int c, boolean ouv){
        super(l,c,0); // feu = 0 par defaut
        this.ouverte = ouv;
    }
    public boolean isOuverte() {
        return ouverte;
    }
    @Override
    public boolean estTraversable() {
        return ouverte;
    }
    public void ouvrirPorte(){ouverte = true;}
    @Override
    public boolean haveCles() {
        return false;
    }
}
