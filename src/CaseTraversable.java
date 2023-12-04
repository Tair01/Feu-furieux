public class CaseTraversable extends Case{
    private Joueur j;
    public CaseTraversable(int l, int c){
        super(l,c);
        this.j = null;
    }

    @Override
    public boolean estTraversable() {
        return true;      //Comme c'est une case  traversable c'est toujours true
    }
    public boolean haveJ(){return j!= null;} //Est-ce qu'il y a un joueur dans la case ou pas
    @Override
    public void vide() {
        j = null;
    }

    @Override
    public void entre(Joueur j) {
        this.j = j;
        j.setCase(this);
    }

    @Override
    public Joueur getJ() {
        return j;
    }

    @Override
    public int getChaleur() {
        return j.getChaleur();
    }

    public boolean haveCles(){return false;} //Est-ce qu'il y a un cles dans la case: non

}
