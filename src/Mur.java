public class Mur extends Case{
    public Mur(int l, int c){
        super(l,c);
    }

    @Override
    public boolean estTraversable() {
        return false;
    }
}
