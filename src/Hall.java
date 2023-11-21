public class Hall extends Case{
    private boolean ouverte;
    private int chaleur;
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
    }
}
