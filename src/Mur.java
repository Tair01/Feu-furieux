public class Mur extends CaseTraversable{
    public Mur(int l, int c){
        super(l,c);
    }

    @Override
    public boolean estTraversable() {
        return false;
    }
}
