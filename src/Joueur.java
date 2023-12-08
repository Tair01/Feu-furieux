public class Joueur {
    private CaseTraversable c;
    private int resistance;
    private int cles ;
    private boolean gagner;
    public Joueur(CaseTraversable c, int r, int k) {
        this.c = c;
        this.resistance = r;
        this.cles = k;
    }
    public int getCles(){
        return cles;
    }
    public CaseTraversable getC(){
        return c;
    }
    public int getResistance() {
        return resistance;
    }
    public void setResistance(int a){resistance = a;}
    public void ajouterRes(int chaleur){
        resistance = resistance - chaleur;
        if(resistance < 0){resistance = 0;}
    }
    public void prendCles(){
        cles++;
    }
    public void useCles(){
        cles--;
    }
    public boolean isGagner(){return gagner;} // est-ce que le joueur a gagner?
    public void gagner(){gagner = true;} // joueur a gagner
    public void attackDuFeu(int feu){
        resistance = resistance - feu;
    }
    public void bouge(Case cible) {
        if (cible.estTraversable()) {
            System.out.println("Case traversable, mouvement en cours...");
            c.vide();
            c = (CaseTraversable) cible;
            c.entre(this);
        }else{
            if (cible instanceof Porte p && this.getCles() > 0) {
                useCles();
                p.ouvrirPorte();
                System.out.println("La porte est ouverte");
            }
        }
        if(cible instanceof Sortie){
            this.gagner();
        }
    }
}
