public class Joueur {
    private CaseTraversable c;
    private int resistance;
    private int cles;
    private Direction direction;
    private boolean gagner;
    public Joueur(CaseTraversable c, int r, int k) {
        this.c = c;
        this.resistance = r;
        this.cles = k;
    }

    public int getChaleur(){
        return this.c.getChaleur();
    }
    public int getCles(){
        return this.cles;
    }
    public CaseTraversable getC(){
        return this.c;
    }
    public Direction getDirection(){
        return this.direction;
    }
    public int getResistance() {
        return this.resistance;
    }
    public void setDirection(Direction d){this.direction = d;}
    public void ajouterRes(int chaleur){
        resistance = resistance - chaleur;
        if(resistance < 0){resistance = 0;}
    }
    public void prendCles(){this.cles++;}
    public boolean isGagner(){return this.gagner;} // est-ce que le joueur a gagner?
    public  void gagner(){this.gagner = true;} // joueur a gagner

    public void bouge(Case cible) {
        if (cible.estTraversable()) {
            c.vide();
            cible.entre(this);
            c = (CaseTraversable) cible;
        }
    }
    public void setCase(CaseTraversable nouvelleCase) {
        this.c = nouvelleCase;
    }

}
