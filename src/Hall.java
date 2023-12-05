public class Hall extends CaseTraversable{
    private boolean cle;
    private int chaleur;
    public Hall(int l, int c){
        super(l,c);
    }
    public Hall(int l, int c, boolean cl){
        super(l,c);
        this.cle = cl;
    }

    public Hall(int l, int c,int ch){
        super(l,c);
        this.chaleur = ch;
    }

    public boolean hCles(){
        return this.cle;
    }
    @Override
    public boolean estTraversable() {
        return true;
    }

    public int getChaleur() {
        return chaleur;
    }
    public void entre(Joueur j) {
        super.entre(j);
    } // On peut rajouter des joueurs en utilisant cette méthode
}
