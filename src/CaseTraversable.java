public  abstract class CaseTraversable extends Case{
    private Joueur j;
    private boolean enFeu;
    public CaseTraversable(int l, int c){
        super(l,c);
        this.j = null;
        this.enFeu = false;
    }

    public boolean isEnFeu(){
        return enFeu;
    }
    public void mettreFeu(){
        enFeu = true;
    }
    public void eteindreFeu(){
        enFeu = false;
    }
    @Override
    public boolean estTraversable() {return true;} //Comme c'est une case  traversable c'est toujours true
    public boolean haveJ(){return j!= null;} //Est-ce qu'il y a un joueur dans la case ou pas
    public abstract boolean haveCles(); //Est-ce qu'il y a un cles dans la case

    public void vide() {
        j = null;
    }

    public void entre(Joueur j) {
        this.j = j;
    }

    @Override
    public void ajouterRes(int c) {
        int a = j.getResistance();
        a = a - c;
        if(a < 0){j.setResistance(0);}
    }
}
