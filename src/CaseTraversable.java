public class CaseTraversable extends Case{
    private Joueur j;
    public CaseTraversable(int l, int c){
        super(l,c);
        this.j = null;
    }

    @Override
    public boolean estTraversable() {
        return false;
    }

    @Override
    public void vide() {
        j = null;
    }

    @Override
    public void entre(Joueur j) {
        this.j = j;
    }

    @Override
    public Joueur getJ() {
        return j;
    }
    public int getChaleur(){
        return getChaleur();
    }
}
