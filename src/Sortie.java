public class Sortie  extends CaseTraversable{
    public Sortie(int l, int c, int ch){
        super(l,c);
    }
    @Override
    public boolean estTraversable() {
        return true;
    }
    @Override
    public void entre(Joueur j) {super.entre(j);}
    @Override
    public boolean haveCles() {
        return false;
    }//Parce que c'est une sortie
}
