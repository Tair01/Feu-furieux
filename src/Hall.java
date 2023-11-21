public class Hall extends Case implements CaseTraversable{
    private boolean ouverte;
    private int chaleur;
    private Joueur joueur;
    public Hall(int l, int c){
        super(l,c);
        this.ouverte = false; //Par defaut
        this.chaleur = 0;     //Par defaut
    }
    public Hall(int l, int c, boolean o){
        super(l,c);
        this.ouverte = o;
        this.chaleur = 0; //Par defaut
    }

    public Hall(int l, int c,int ch){
        super(l,c);
        this.ouverte = false; //Par defaut
        this.chaleur = ch;
    }

    public boolean isOuverte() {
        return ouverte;
    }

    public int getChaleur() {
        return chaleur;
    }

    @Override
    public boolean estTraversable() {
        return ouverte;
    }

    public void entre(Joueur j) {
        joueur = j;             // On peut rajouter des joueurs en utilisant cette méthode
    }

    public void vide(){
        joueur = null;            // On peut vider la case en utilisant cette méthode
    }

    @Override
    public Joueur getJ() {        //Recuperer le joueur
        return joueur;
    }

    public void ouvrirPorte(){ouverte = true;}
    public void fermetPorte(){ouverte = false;}
}
