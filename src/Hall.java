public class Hall extends CaseTraversable{
    private boolean cle;
    public Hall(int l, int c,int f){
        super(l,c,f);
    }
    public Hall(int l, int c, boolean cl){
        super(l,c,0);
        this.cle = cl;
    }
    public Hall(int l, int c){
        this(l,c,false);
    }
    public void entre(Joueur j) {
        super.entre(j);
        if(haveCles()) {
            j.prendCles();
            this.cle = false;
        }
    } // On peut rajouter des joueurs en utilisant cette méthode
    public boolean haveCles(){
        return this.cle;
    }
    @Override
    public boolean estTraversable() {return true; }


}
