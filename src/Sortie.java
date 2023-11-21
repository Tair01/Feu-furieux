public class Sortie  extends CaseTraversable{
    private int chaleur;
    public Sortie(int l, int c, int ch){
        super(l,c);
        this.chaleur = ch;
    }

    public int getChaleur() {
        return chaleur;
    }

    @Override
    public boolean estTraversable() {
        return true;
    }

}
