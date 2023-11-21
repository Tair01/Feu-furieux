public class Sortie  extends Case{
    private int chaleur;
    public Sortie(int l, int c, int ch){
        super(l,c);
        this.chaleur = ch;
    }

    @Override
    public boolean estTraversable() {
        return true;
    }

}
