public  abstract class CaseTraversable extends Case{
    private Joueur j;
    private int enFeu;
    public CaseTraversable(int l, int c,int feu){
        super(l,c);
        this.j = null;
        this.enFeu = feu;
    }

    public int getEnFeu(){
        return enFeu;
    }
    public void mettreEnFeu() {
        if(10 > enFeu){enFeu++;}
    }
    public void eteindreEnFeu(){
        if(enFeu >0 ){enFeu--;}
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
        //j.attackDuFeu(enFeu); //Pas encore tester!!
    }

    @Override
    public void ajouterRes(int c) {
        int a = j.getResistance();
        a = a - c;
        if(a < 0){j.setResistance(0);}
    }
}
