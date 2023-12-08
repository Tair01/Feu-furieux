public class Mur extends CaseTraversable{
    public Mur(int l, int c){
        super(l,c,0);
    }

    @Override
    public boolean estTraversable() {
        return false;
    }

    @Override
    public boolean haveCles() {return false;} // Parce que c'est une mur
}
