public class Sortie  extends CaseTraversable{
    private int chaleur;
    public Sortie(int l, int c, int ch){
        super(l,c);
        this.chaleur = ch;
    }
    @Override
    public boolean estTraversable() {
        return true;
    }
    @Override
    public void entre(Joueur j) {
        super.entre(j);
        j.gagner();
    }
    public int getChaleur(){
        return chaleur;
    }
    @Override
    public boolean haveCles() {
        return false;
    }//Parce que c'est une sortie
}
