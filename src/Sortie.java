public class Sortie  extends CaseTraversable{
    public Sortie(int l, int c, int feu){
        super(l,c,feu);
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
    @Override
    public boolean haveCles() {
        return false;
    }//Parce que c'est une sortie
}
