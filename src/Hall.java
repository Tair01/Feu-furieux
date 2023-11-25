public class Hall extends CaseTraversable{
    private boolean ouverte;
    private int chaleur;
    private Joueur joueur;
    public Hall(int l, int c){
        super(l,c);
    }
    public Hall(int l, int c, boolean o){
        super(l,c);
        this.ouverte = o;
    }

    public Hall(int l, int c,int ch){
        super(l,c);
        this.chaleur = ch;
    }

    public boolean isOuverte(){
        return ouverte;
    }

    @Override
    public boolean estTraversable() {
        return true;
    }

    public int getChaleur() {
        return chaleur;
    }
    public void entre(Joueur j) {
        this.joueur = j;             // On peut rajouter des joueurs en utilisant cette méthode
    }
    @Override
    public Joueur getJ() {        //Recuperer le joueur
        return joueur;
    }
}
